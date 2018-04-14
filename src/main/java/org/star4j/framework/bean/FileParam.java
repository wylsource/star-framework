package org.star4j.framework.bean;

import java.io.InputStream;

/**
 * @Author: WuYL
 * @Description: 封装上传文件的参数
 * @Date: Created on 2017/7/26
 * @Modified By:
 */
public class FileParam {

    /**
     * 文件表单的字段名
     */
    private String fieldName;
    /**
     * 上传文件的文件名
     */
    private String fileName;
    /**
     * 上传文件的文件大小
     */
    private long fileSize;
    /**
     * 上传文件的 Content-Type (可以用于判断文件类型)
     */
    private String contentType;
    /**
     * 上传文件的字节输入流
     */
    private InputStream inputStream;


    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
