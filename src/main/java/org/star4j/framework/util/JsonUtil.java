package org.star4j.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: WuYL
 * @Description: Json 工具类
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class JsonUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e){
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json,Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (Exception e){
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
