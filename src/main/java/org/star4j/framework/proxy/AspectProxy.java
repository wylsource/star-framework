package org.star4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

/**
 * @Author: WuYL
 * @Description: 切面代理
 * @Date: Created on 2017/7/20
 * @Modified By:
 */
public abstract class AspectProxy implements Proxy {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    /**
     * 代理方法
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        /**
         * 开始方法（前置增强）
         */
        begin();
        try {
            if (intercept(cls, method, params)){
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            }else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e){
            logger.error("proxy failure", e);
            error(cls, method, params);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    /**
     * 开始方法
     */
    public void begin(){}

    /**
     * 拦截方法
     */
    public boolean intercept(Class<?> cls, Method method, Object[] params)throws Throwable{
        return true;
    }

    /**
     * 前置增强
     */
    public void before(Class<?> cls, Method method, Object[] params)throws Throwable{
    }

    /**
     * 后置增强
     */
    public void after(Class<?> cls, Method method, Object[] params)throws Throwable{
    }

    /**
     * 抛出增强
     */
    public void error(Class<?> cls, Method method, Object[] params)throws Throwable{
    }

    /**
     * 最终方法
     */
    public void end(){}

}
