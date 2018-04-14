package org.star4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author: WuYL
 * @Description: 编码与解码操作工具类
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class CodeUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e){
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e){
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
