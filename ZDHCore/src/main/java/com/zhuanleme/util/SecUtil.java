package com.zhuanleme.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: secUtil.java</p>
 * <p/>
 * <p>Description: secUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/8/13
 */
public class SecUtil {

    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件转换成MD5
     * @param file
     * @return
     */
    public static String fileMD5(File file) {
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
        ) {
            int len = 0;
            byte[] bytes = new byte[8192];
            while ((len=fileInputStream.read(bytes))!=-1){
                MD5.update(bytes,0,len);
            }
            return new BigInteger(1,MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
