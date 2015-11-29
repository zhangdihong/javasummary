package com.zhuanleme.bean;

import com.zhuanleme.util.ValidUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

/**
 * <p>Project: com.zhuanleme.bean</p>
 * <p>Title: BeanFactory.java</p>
 * <p/>
 * <p>Description: BeanFactory </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/29
 */
public class BeanFactory {
    static {

    }

    /**
     * 存放BeanUtil中的javaBean 数据
     *
     */
    protected static Map<String, Map<String, BeanStruct>> BEAN_SIMPLE_PROPERTIES = new Hashtable<>();
    /**
     * 存放BeanUtil 解析过的javaBean 数据
     */
    protected static Map<String, Map<String, BeanStruct>> BEAN_SIMPLE_PROPERTIES_IGNORE = new Hashtable<>();
    public static boolean isDeclaredField(String className, String pro) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        Field[] fields = clazz.getFields();
        if (ValidUtil.isValid(fields)) {
            for (Field field : fields) {
                if (field.equals(pro)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void add(Object obj){
        add(obj);
    }

    public static void  add(Class clazz) throws IntrospectionException, ClassNotFoundException {
        String className = clazz.getName();
        if (!ValidUtil.isValid(BEAN_SIMPLE_PROPERTIES.get(className))) {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDes = beanInfo.getPropertyDescriptors();
            if(ValidUtil.isValid(propertyDes)){
                Map<String, BeanStruct> simpleProperty = new Hashtable<>();
                Map<String, BeanStruct> simplePropertyIgnore = new Hashtable<>();
                for (PropertyDescriptor propertyDe : propertyDes) {
                    String filedName = propertyDe.getName();
                    if(!"class".equals(filedName)){
                        Object obj = propertyDe.getPropertyType();
                        Method readMethod = propertyDe.getReadMethod();
                        Method writeMethod = propertyDe.getWriteMethod();
                        boolean isDeclared = isDeclaredField(className, filedName);
                        simpleProperty.put(filedName, new BeanStruct(filedName, obj, readMethod, writeMethod, isDeclared));
                        simplePropertyIgnore.put(filedName.toLowerCase(), new BeanStruct(filedName, obj, readMethod, writeMethod, isDeclared));
                    }
                }
                BEAN_SIMPLE_PROPERTIES.put(className,simpleProperty);
                BEAN_SIMPLE_PROPERTIES_IGNORE.put(className,simpleProperty);

            }
        }

    }
}
