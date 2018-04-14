package org.star4j.framework.bean;

/**
 * @Author: WuYL
 * @Description: 返回结果数据对象
 * @Date: Created on 2017/6/17
 * @Modified By:
 */
public class Data {

    /**
     * 数据模型
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
