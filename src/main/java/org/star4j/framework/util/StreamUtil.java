package org.star4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: WuYL
 * @Description: 流操作工具类
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public final class StreamUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     */
    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader((is)));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e){
            LOGGER.error("get string failure", e);
        }
        return sb.toString();
    }

    /**
     * 将输入流复制到输出流
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        int length;
        byte[] buffer = new byte[4 * 1024];
        try {
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("copy file failure", e);
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error("close stream failure", e);
            }
        }
    }
}
