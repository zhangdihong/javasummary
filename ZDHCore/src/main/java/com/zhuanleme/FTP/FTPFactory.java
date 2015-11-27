package com.zhuanleme.FTP;

import java.net.URL;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FTPFactory.java</p>
 * <p/>
 * <p>Description: FTPFactory </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/22
 */
public class FTPFactory {

    private static String CONFIG_FILE;
    static {
        URL path = FTPFactory.class.getProtectionDomain().getCodeSource().getLocation();
        CONFIG_FILE = path.getPath() + "ftp.properties";
    }

    public static FTPUtil getInstance(String name){
        return null;
    }

}
