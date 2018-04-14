package org.star4j.framework.bean;

import org.star4j.framework.util.CastUtil;
import org.star4j.framework.util.CollectionUtil;
import org.star4j.framework.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WuYL
 * @Description: 请求参数对象
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class Param {

    /**
     * 封装表单参数集合
     */
    private List<FormParam> formParamList;
    /**
     * 封装上传文件集合
     */
    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    /**
     * 获取请求参数映射
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        if (CollectionUtil.isNotEmpty(formParamList)){
            for (FormParam formParam : formParamList){
                String fieldName = formParam.getFieldName();
                String fieldValue = (String) formParam.getFieldValue();
                if (fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取上传文件映射
     */
    public Map<String, List<FileParam>> getFileMap(){
        Map<String, List<FileParam>> fieldMap = new HashMap<String, List<FileParam>>();
        if (CollectionUtil.isNotEmpty(fileParamList)){
            for (FileParam fileParam : fileParamList){
                String fieldName = fileParam.getFieldName();
                List<FileParam> fileParamList;
                if (fieldMap.containsKey(fieldName)){
                    fileParamList = fieldMap.get(fieldName);
                } else {
                    fileParamList = new ArrayList<FileParam>();
                }
                fileParamList.add(fileParam);
                fieldMap.put(fieldName, fileParamList);
            }
        }
        return fieldMap;
    }

    /**
     * 获取所有上传文件
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    /**
     * 获取唯一上传文件
     */
    public FileParam getFile(String fieldName){
        List<FileParam> fileParamList = getFileList(fieldName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
    }

    /**
     * 存储请求参数 （Key 为参数名，Value 为参数值）
     */
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取 String 型参数值
     */
    public String getString(String name){
        return CastUtil.castString(getFileMap().get(name));
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(getFileMap().get(name));
    }

    /**
     * 根据参数名获取 int 型参数值
     */
    public int getInt(String name){
        return CastUtil.castInt(getFileMap().get(name));
    }

    /**
     * 根据参数名获取 Double 型参数值
     */
    public double getDouble(String name){
        return CastUtil.castDouble(getFileMap().get(name));
    }

    /**
     * 根据参数名获取 Boolean 型参数值
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFileMap().get(name));
    }


    /**
     * 获取所有字段信息
     */
    public Map<String,Object> getMap(){
        return paramMap;
    }
}
