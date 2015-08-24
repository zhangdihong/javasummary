package com.zhuanleme.util;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: ProUtil.java</p>
 * <p/>
 * <p>Description: 提供一些常用的属性文件相关的方法 </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/7/29
 */
public class ProUtil {

    /**
     * 从系统属性文件中获取相应的值
     *
     * @param key key
     * @return 返回value
     */
    public static String key(String key) {
        return System.getProperty(key);
    }

    /**
     * 根据key读取Value
     * @param filePath 属性文件
     * @param key 需要读取的属性
     * @return
     */
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try (
                InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        ) {
            pps.load(in);
            return pps.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取Properties的全部信息
     * @param filePath 读取的属性文件
     * @return 返回所有的属性 key：value
     *
     */
    public static String GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        String str = "";
        try (
                InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        ) {
            pps.load(in);
            Enumeration en = pps.propertyNames();
            while(en.hasMoreElements()){
                String strKey = (String)en.nextElement();
                String strValue = pps.getProperty(strKey);
                str += strKey + ":" + strValue + "<>";
            }
            return str.substring(0, str.lastIndexOf("<>"));
        }
    }

    /**
     * 写入Properties信息
     *
     * @param filePath 写入的属性文件
     * @param propKey  属性名称
     * @param propValue 属性值
     */
    public  static void writeProperties(String filePath,String propKey,String propValue) throws IOException{
        Properties props = new Properties();

        props.load(new FileInputStream(filePath));

        OutputStream fos = new FileOutputStream(filePath);
        props.setProperty(propKey,propValue);
        props.store(fos, propKey);
    }
}
