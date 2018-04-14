package org.star4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Author: WuYL
 * @Description: 数组工具类
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class ArrayUtil {

    /**
     * 判断数组是否为空
     */
    public static Boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array){
        return !isEmpty(array);
    }
}
