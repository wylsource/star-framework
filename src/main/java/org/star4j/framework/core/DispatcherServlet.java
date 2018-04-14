package org.star4j.framework.core;

import org.star4j.framework.ConfigConstant;
import org.star4j.framework.HelpLoader;
import org.star4j.framework.helper.*;
import org.star4j.framework.util.*;
import org.star4j.framework.bean.Data;
import org.star4j.framework.bean.Handler;
import org.star4j.framework.bean.Param;
import org.star4j.framework.bean.View;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WuYL
 * @Description: 请求转发器
 * @Date: Created on 2017/7/17
 * @Modified By:
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化相关 Helper 类
        HelpLoader.init();
        //获取 ServletContext 对象（用于注册 Servlet）
        ServletContext servletContext = config.getServletContext();
        //注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
        /** 初始化文件上传功能 */
        UploadHelper.init(servletContext);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try {
            //获取请求方法与请求路径
            String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();
            /**
             * 过滤ico请求
             */
            if (requestPath.equals("/favicon.ico")){
                return;
            }
            //获取 Action 处理器
            Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
            if (handler != null){
                //获取 Controller 类及其 Bean 实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);
                //创建请求参数对象
                Param param;
                if (UploadHelper.isMultipart(request)){
                    param = UploadHelper.createParam(request);
                } else {
                    param = RequestHelper.createParam(request);
                }
                 //调用 Action 方法
                Method actionMethod = handler.getActionMethod();
                Object result;
                if (param.isEmpty()){
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }
                //处理 Action 方法返回值
                if (result instanceof View){
                    //返回 JSP 页面
                    handleViewResult((View) result, request, response);
                }else if (result instanceof Data){
                    //返回 JSON 数据
                    handleDataResult((Data) result, response);
                }
            }
        } finally {
            ServletHelper.destory();
        }
    }


    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)){
            if (path.startsWith("/")){
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()){
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }

    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if (model != null){
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
