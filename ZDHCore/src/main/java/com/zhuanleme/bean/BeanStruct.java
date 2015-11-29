package com.zhuanleme.bean;

import java.lang.reflect.Method;

/**
 * <p>Project: com.zhuanleme.bean</p>
 * <p>Title: BeanStruct.java</p>
 * <p/>
 * <p>Description: BeanStruct </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/29
 */
public class BeanStruct {

    //字段名
    private String fieldName;
    //字段类型
    private Object fieldType;
    //读取方法
    private Method readMethod;
    //写入方法
    private Method writeMethod;

    private boolean isDeclared;



    public BeanStruct(String fieldName, Object fieldType, Method readMethod, Method writeMethod, boolean isDeclared) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
        this.isDeclared = isDeclared;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldType() {
        return fieldType;
    }

    public void setFieldType(Object fieldType) {
        this.fieldType = fieldType;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }

    public boolean isDeclared() {
        return isDeclared;
    }

    public void setIsDeclared(boolean isDeclared) {
        this.isDeclared = isDeclared;
    }
}
