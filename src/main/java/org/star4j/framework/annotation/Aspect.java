package org.star4j.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: WuYL
 * @Description: 切面注解
 * @Date: Created on 2017/7/19
 * @Modified By:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解类 (用于定义 Controller 这类注解)
     */
    Class<? extends Annotation> value();

}
