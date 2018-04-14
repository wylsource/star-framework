package org.star4j.framework.proxy;

/**
 * @Author: WuYL
 * @Description: 代理接口
 * @Date: Created on 2017/7/19
 * @Modified By:
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
