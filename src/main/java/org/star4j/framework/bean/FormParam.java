package org.star4j.framework.bean;

/**
 * @Author: WuYL
 * @Description: 封装表单参数
 * @Date: Created on 2017/7/26
 * @Modified By:
 */
public class FormParam {

    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
