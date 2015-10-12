package com.zhuanleme.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: ClassUtil.java</p>
 * <p/>
 * <p>Description: ClassUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * <p>Company: 华炜云商科技有限公司 www.hwtech.cc</p>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/9
 */
public class ClassUtil {

    public static String[] getField(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        Field[] fields = clazz.getFields();
        Field[] fieldz = clazz.getDeclaredFields();
        Set<String> set = new HashSet<>();
        for (Field field: fields){
            set.add(field.getName());
        }
        for (Field field: fieldz){
            set.add(field.getName());
        }
        return set.toArray(new String[set.size()]);
    }

    public static String[] getMethod(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        Set<String> set = new HashSet<>();

        for (Method method : methods){
            set.add(method.getName());
        }
        return set.toArray(new String[set.size()]);
    }

    public static void setter(Object object,String attr,Object value,Class<?> type){
        try {
            Method method = object.getClass().getMethod("sett"+initStr(attr),type);
            method.invoke(object,value);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static String initStr(String string){
        return string.substring(0,1).toLowerCase() +string.substring(1);
    }

//    public static List<String> getClassName(String packageName,boolean ischildPackage){
//        List<String> fileNames = null;
//        String packagePath = packageName.replace(".","/");
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//    }
//    public static List<String> getClassNameByFile(String filePath,List<String> className,boolean childPackage){
//        List<String> myClassName = new ArrayList<>();
//        File file = new File(filePath);
//        File[] files = file.listFiles();
//        if(ValidUtil.isValid(file)){
//            for (File )
//        }
//
//    }

}
