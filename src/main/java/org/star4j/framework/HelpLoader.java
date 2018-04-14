package org.star4j.framework;

import org.star4j.framework.util.ClassUtil;
import org.star4j.framework.helper.*;

/**
 * @Author: WuYL
 * @Description: 加载相应的 Helper 类
 * @Date: Created on 2017/7/17
 * @Modified By:
 */
public final class HelpLoader {

    /**
     * 初始化方法
     */
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
