package org.star4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WuYL
 * @Description: 代理链
 * @Date: Created on 2017/7/19
 * @Modified By:
 */
public class ProxyChain {

    /**
     * 目标类
     */
    private final Class<?> targetClass;
    /**
     * 目标方法
     */
    private final Method targetMethod;
    /**
     * 目标对象
     */
    private final Object targetObject;
    /**
     * 方法代理
     */
    private final MethodProxy methodProxy;
    /**
     * 方法参数
     */
    private final Object[] methodParams;
    /**
     * 代理列表
     */
    private List<Proxy> proxyList = new ArrayList<Proxy>();
    /**
     * 代理索引
     */
    private int proxyIndex = 0;


    /**
     * 构造方法初始化
     */
    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.targetObject = targetObject;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
