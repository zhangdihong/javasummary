package com.zhuanleme.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: ClassUtil.java</p>
 * <p/>
 * <p>Description: ClassUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
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
        for (Field field : fields) {
            set.add(field.getName());
        }
        for (Field field : fieldz) {
            set.add(field.getName());
        }
        return set.toArray(new String[set.size()]);
    }

    public static String[] getMethod(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        Set<String> set = new HashSet<>();
        for (Method method : methods) {
            set.add(method.getName());
        }
        return set.toArray(new String[set.size()]);
    }

    public static void setter(Object object, String attr, Object value, Class<?> type) {
        try {
            Method method = object.getClass().getMethod("sett" + initStr(attr), type);
            method.invoke(object, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static String initStr(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    public static List<String> getClassName(String packageName, boolean ischildPackage) {
        List<String> fileNames = null;
        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url =  classLoader.getResource(packagePath);
        if(null != url){
            String type = url.getProtocol();
            if(type.equals("file")){
                fileNames = getClassNameByFile(url.getPath(), null, ischildPackage);
            }else if(type.equals("jar")){
                fileNames = getClassNameByJar(url.getPath(), ischildPackage);
            }
        }else{
            fileNames = getClassNameByJars(((URLClassLoader)classLoader).getURLs(),packagePath,ischildPackage);
        }
        if(null != fileNames){
            List<String> temp = new ArrayList<>();
            for (String name : fileNames) {
                name = name.substring(name.indexOf(packageName),name.length());
                temp.add(name);
            }
            fileNames = temp;
        }
        return fileNames;
    }

    public static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childfiles = file.listFiles();
        if (ValidUtil.isValid(file)) {
            for (File childfile : childfiles) {
                if (childfile.isDirectory()) {
                    if (childPackage) {
                        myClassName.addAll(getClassNameByFile(childfile.getPath(), myClassName, childPackage));
                    }
                } else {
                    String childFilePath = childfile.getPath();
                    if (childFilePath.endsWith(".class")) {
                        childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                        childFilePath = childFilePath.replace("\\", ".");
                        myClassName.add(childFilePath);
                    }
                }
            }
        }
        return myClassName;
    }

    public static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);
        try (JarFile jarFile = new JarFile(jarFilePath);) {
            Enumeration<JarEntry> entryEnumeration = jarFile.entries();
            while (entryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = entryEnumeration.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myClassName;
    }

    public static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassNae = new ArrayList<>();
        if (null != urls) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassNae.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassNae;
    }

    public Class loadClass(String className) throws ClassNotFoundException {
        Class clazz = null;
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            try {
                clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            } catch (ClassNotFoundException e1) {
                clazz = getClass().getClassLoader().loadClass(className);
            }
        }
        return clazz;
    }
}
