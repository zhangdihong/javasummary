package com.zhuanleme.bean;

import com.zhuanleme.Collection.CollectionUtil;
import com.zhuanleme.util.ValidUtil;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Project: com.zhuanleme.bean</p>
 * <p>Title: BeanUtil.java</p>
 * <p/>
 * <p>Description: BeanUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/29
 */
public class BeanUtil {

    public static Map<String, BeanStruct> getBeanStruct(Object object){
        return BeanFactory.BEAN_SIMPLE_PROPERTIES.get(object.getClass().getName());
    }

    public static Map<String, BeanStruct> getBeanStructIgnore(Object object){
        return BeanFactory.BEAN_SIMPLE_PROPERTIES_IGNORE.get(object.getClass().getName());
    }

    public static Method getReadMethod(Object object, String pro){
        BeanStruct beanStruct = getBeanStruct(object).get(pro);
        return beanStruct.getReadMethod();
    }

    public static Method getWriteMethod(Object object, String pro){
        BeanStruct beanStruct = getBeanStruct(object).get(pro);
        return beanStruct.getWriteMethod();
    }

    public static Method getReadMethodIgnore(Object object, String pro){
        BeanStruct beanStruct = getBeanStructIgnore(object).get(pro);
        return beanStruct.getReadMethod();
    }

    public static Method getWriteMethodIgnore(Object object, String pro){
        BeanStruct beanStruct = getBeanStructIgnore(object).get(pro);
        return beanStruct.getWriteMethod();
    }
    public static Object readMethod(Object bean, Method readMethod) throws InvocationTargetException, IllegalAccessException {
        return readMethod.invoke(bean);
    }
    public static Object writeMethod(Object bean, Method writeMethod, Object value) throws InvocationTargetException, IllegalAccessException {
        return writeMethod.invoke(bean, value);
    }

    public static void add(Object object){
        BeanFactory.add(object);
    }
    public static void add(Class clazz) throws IntrospectionException, ClassNotFoundException {
        BeanFactory.add(clazz);
    }
    public static boolean hasProperty(Object bean, String pro){
        add(bean);
        Map map = getBeanStruct(bean);
        return map.containsKey(pro);
    }
    public static boolean hasDeclaredProperty(Object bean, String pro){
        add(bean);
        Map map = getBeanStruct(bean);
        BeanStruct beanStruct = (BeanStruct)map.get(pro);
        return ValidUtil.isValid(beanStruct) && beanStruct.isDeclared();
    }
    public static boolean hasDeclaredPropertyIgnoreCase(Object bean, String pro){
        add(bean);
        Map map = getBeanStructIgnore(bean);
        return map.containsKey(pro.toLowerCase());

    }
    public static boolean hasPropertyFilter(Object bean, String pro, PropertyFilter filter){
        add(bean);
        pro = filter.Properties(pro);
        Map<String, BeanStruct> beanStructMap = getBeanStruct(bean);
        if(ValidUtil.isValid(beanStructMap)){
            Set<String> set = beanStructMap.keySet();
            for (String s : set) {
                if(pro.equals(filter.Properties(s))){
                    return true;
                }
            }
        }
        return false;
    }
    public static Object getProperty(Object bean, String pro) throws InvocationTargetException, IllegalAccessException {
        add(bean);
        return  readMethod(bean, getReadMethod(bean, pro));
    }

    /**
     * 获取对象的属性
     * @param bean
     * @param pro
     * @return
     */
    public static Object getPropertyPeaceful(Object bean, String pro){
        add(bean);
        Object result = null;
        try {
            result = readMethod(bean, getReadMethod(bean, pro));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取对象属性(忽略属性名字大小写)
     * @param bean
     * @param pro
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getPropertyIgnoreCase(Object bean, String pro) throws InvocationTargetException, IllegalAccessException {
        add(bean);
        return  readMethod(bean, getReadMethodIgnore(bean, pro));
    }
    public static Object getPropertyIgnoreCasePeaceful(Object bean, String pro){
        add(bean);
        Object result = null;
        try {
            result = readMethod(bean, getReadMethodIgnore(bean, pro));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Object getPropertyFilter(Object bean, String pro, PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
        add(bean);
        Object result = null;
        pro = filter.Properties(pro);
        Map<String, BeanStruct> beanStructMap = new HashMap<>();
        if (ValidUtil.isValid(beanStructMap)) {
            Set<String> set = beanStructMap.keySet();
            for (String s : set) {
                if(pro.equals(filter.Properties(s))){
                    result = readMethod(bean, getReadMethod(bean, s));
                }
            }
        }
        return result;
    }
    public static void setProperty(Object bean, String pro, Object value){
        add(bean);
        try {
            writeMethod(bean, getWriteMethod(bean, pro), value);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void setPropertyFilter(Object bean, String pro, Object value, PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
        add(bean);
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = getBeanStruct(bean);
        if (ValidUtil.isValid(map)) {
            Set<String> set = map.keySet();
            for (String s : set) {
                if(pro.equals(filter.Properties(s))){
                    writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
                }
                
            }
        }
    }
    public static void setPropertyFilterPeaceful(Object bean, String pro, Object value, PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
            add(bean);
            pro = filter.Properties(pro);
            Map<String, BeanStruct> map = getBeanStruct(bean);
        if (ValidUtil.isValid(map)) {
            Set<String> set = map.keySet();
            for (String s : set) {
                if (pro.equals(filter.Properties(s))) {
                    writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
                }
            }
        }
    }

    /**
     * 拷贝对象指定的属性
     * @param srcBean
     * @param destBean
     * @param pros
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperty(Object srcBean, Object destBean, String[] pros) throws InvocationTargetException, IllegalAccessException {
        add(srcBean);
        add(destBean);
        if (ValidUtil.isValid(pros)) {
            for (String pro : pros) {
                Object value = readMethod(srcBean, getReadMethod(srcBean, pro));
                writeMethod(destBean, getWriteMethod(destBean , pro), value);
            }
        }
    }

    /**
     * 拷贝对象指定的属性
     * @param srcBean
     * @param destBean
     * @param pros
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyPropertyPeaceful(Object srcBean, Object destBean, String[] pros) throws InvocationTargetException, IllegalAccessException {
        add(srcBean);
        add(destBean);
        if (ValidUtil.isValid(pros)) {
            for (String pro : pros) {
                Object value = readMethod(srcBean, getReadMethod(srcBean, pro));
                writeMethod(destBean, getWriteMethod(destBean, pro), value);
            }
        }
    }
    public static void copyProperties(Object srcBean, Object destBean) throws InvocationTargetException, IllegalAccessException {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap =  getBeanStruct(srcBean);
        Map<String, BeanStruct> destMap =  getBeanStruct(destBean);
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, destMap);
        for (Map.Entry<String, BeanStruct> entry : intersection.entrySet()){
            String key = entry.getKey();
            Object value = readMethod(srcBean, getReadMethod(srcBean, key));
            writeMethod(destBean, getWriteMethod(destBean, key), value);
        }
    }
    public static void copyPropertiesIgnore(Object srcBean, Object destBean, PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = getBeanStruct(srcBean);
        Map<String, BeanStruct> destMap = getBeanStruct(destBean);
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, destMap);
        for (Map.Entry<String, BeanStruct> entry : intersection.entrySet()) {
            String key = (String) entry.getKey();
            Object value = readMethod(srcBean, getReadMethodIgnore(srcBean, key));
            writeMethod(destBean, getWriteMethodIgnore(destBean, key), value);
        }
    }
    public static void copyProerties(Object srcBean, Object destBean,PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {

        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = getBeanStruct(srcBean);
        Map<String, BeanStruct> destMap = getBeanStruct(destBean);
        if (ValidUtil.isValid(srcMap, destMap)) {
            Map<String, String> srcMapFilter = new HashMap<>();
            Map<String, String> destMapFilter = new HashMap<>();
            for (Map.Entry<String, BeanStruct> entry : srcMap.entrySet()) {
                srcMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            for (Map.Entry<String, BeanStruct> entry : destMap.entrySet()) {
                destMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            Map<String,String> intersection  = CollectionUtil.intersection(srcMapFilter, destMapFilter);
            if (ValidUtil.isValid(intersection)) {
                for (Map.Entry<String, String> entry : intersection.entrySet()) {
                    String key = entry.getKey();
                    String srcKey = srcMapFilter.get(key);
                    String destKey = destMapFilter.get(key);
                    Object value = readMethod(srcBean, getReadMethod(srcBean, srcKey));
                    writeMethod(destBean, getWriteMethod(destBean,destKey),value);
                }
            }
        }
    }


}
