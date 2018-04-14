package org.star4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.star4j.framework.annotation.Transaction;
import org.star4j.framework.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * @Author: WuYL
 * @Description: 事务代理
 * @Date: Created on 2017/7/21
 * @Modified By:
 */
public class TransactionProxy implements Proxy {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    /**
     * 为有 @Transaction 注解的方法添加事务
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        Boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Exception e){
                DatabaseHelper.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        }else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
