package org.star4j.framework.bean;

import java.lang.reflect.Method;

/**
 * @Author: WuYL
 * @Description: 封装 Action 信息实体
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }
    public Method getActionMethod() {
        return actionMethod;
    }
}
