package com.zhuanleme.util;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: SysUtil.java</p>
 * <p/>
 * <p>Description: 提供些获取系统信息相关的工具方法 </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/7/29
 */
public class SysUtil {

    /**
     * JVM的版本
     */
    public static final String JVM_VERSION = ProUtil.key("java.version");
    /**
     * JVM的编码
     */
    public static final String JVM_ENCODING = ProUtil.key("file.encoding");
    /**
     * JVM默认的临时目录
     */
    public static final String JVM_TEMPDIR = ProUtil.key("java.io.tempdir");
    /**
     * 代理用户
     */
    public static  String HTTP_PROXY_HOST = "http.proxyHost";
    /**
     * 代理用户密码
     */
    public static String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    /**
     * 主机Ip
     */
    public static String HOST_IP;
    /**
     * 主机名
     */
    public static String HOST_NAME;
    /**
     * 主机架构
     */
    public static  String OS_ARCH  = ProUtil.key("os.arch");
    /**
     * 主机类型
     */
    public static  String OS_NAME = ProUtil.key("os.name");
    /**
     * 主机类型版本
     */
    public static String OS_VERSION = ProUtil.key("os.version");
    /**
     * 操作系统类型
     */
    public static String SUN_DESKTOP = ProUtil.key("sun.desktop");
    /**
     * 当前用户
     */
    public static  String CURRENT_USER = ProUtil.key("user.name");
    /**
     * 当前用户的家目录
     */
    public static  String CURRENT_USER_HOME = ProUtil.key("user.home");
    /**
     * 当前用户的工作目录
     */
    public static  String CURRENT_USER_DIR = ProUtil.key("user.dir");
    public static  String FILE_SEPARATOR = ProUtil.key("file.separator");
    public static  String PATH_SEPARATOR = ProUtil.key("path.separator");
    public static  String LINE_SEPARATOR = ProUtil.key("line.separator");
    /**
     * 总的物理内存
     */
    public static long TotalMemorySize;
    private static OperatingSystemMXBean osmxb;
    private static int kb = 1024;

    static {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            HOST_NAME = addr.getHostName();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface netint : Collections.list(nets)){
                if(null != netint.getHardwareAddress()){
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for(InterfaceAddress interfaceAddress : list){
                        InetAddress ip = interfaceAddress.getAddress();
                        if (ip instanceof Inet4Address) {
                            HOST_IP += interfaceAddress.getAddress().toString();
                        }
                    }
                }
            }
            HOST_IP = HOST_IP.replaceAll("null","");

        } catch (Exception e) {
            System.out.println("获取服务器Ip出错");
            e.printStackTrace();
        }
        try {
            osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            TotalMemorySize = osmxb.getTotalPhysicalMemorySize();
        } catch (Exception e) {
            System.out.println("获取系统信息失败");
            e.printStackTrace();
        }
    }

    /**
     * 已使用的物理内存
     */
    public  static long usedMemory(){
        if(ValidUtil.isValid(osmxb)){
            return (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
        }
        return 0;
    }
    /**
     * 获取JVM内存总量
     */
    public static long JVMtotalMemory(){
        return  Runtime.getRuntime().totalMemory() / kb;
    }
    /**
     * 虚拟机空闲内存量
     */
    public static long JVMfreeMemory(){
        return Runtime.getRuntime().maxMemory() / kb;
    }
    /**
     *虚拟机使用最大内存量
     */
    public static long JVMmaxMemory(){
        return Runtime.getRuntime().maxMemory() / kb;
    }
    /**
     *  http proxy 设置
     */
    public static void setHttpProxy(String host,String port, String userName,String password){
        System.getProperties().put("HTTP_PROXY_HOST",host);
        System.getProperties().put("HTTP_PROXY_PORT",port);
        System.getProperties().put("HTTP_PROXY_USER",userName);
        System.getProperties().put("HTTP_PROXY_PASSWORD",password);
    }
    /**
     *  http proxy 设置
     */
    public static void setHttpProxy(String host,String port){
        System.getProperties().put("HTTP_PROXY_HOST",host);
        System.getProperties().put("HTTP_PROXY_PORT",port);
    }

}
