package com.zhuanleme.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: CharsetUtil.java</p>
 * <p/>
 * <p>Description: CharsetUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/19
 */
public class CharsetUtil {

    /**
     * 7位ASCII 字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    public static final String US_ASCII = "US-ASCII";
    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * 8 位UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * 16位 UCS 转换格式，Big Endian(最低地址存放高位字节)字节顺序
     */
    public static final String UTF_16BE = "UTF-16BE";
    /**
     * 16位 UCS 转换格式，Little-endian(最高地址存放低位字节)字节顺序
     */
    public static final String UTF_16LE = "UTF-16LE";
    /**
     * 16位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
     */
    public static final String UTF_16 = "UTF-8";
    /**
     * 中文超大字符集
     */
    public static final String GBK = "GBK";

    /**
     *转换为指定编码方式
     *
     * @param string
     * @param str
     * @param newCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String chageCharset(String string, String str, String newCharset) throws UnsupportedEncodingException {
        if(null != str){
            //以默认字符编码解码字符串
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);
        }
        return null;
    }
    public static String getDefaultCharset() throws UnsupportedEncodingException {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String defaultEcoding = writer.getEncoding();
        return defaultEcoding;
    }

    /**
     * 转换编码
     * @param string
     * @param oldCharset
     * @param newCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String string,String oldCharset,String newCharset) throws UnsupportedEncodingException {
        if (null != string) {
            byte[] b = string.getBytes(oldCharset);
            return new String(b,newCharset);
        }
        return null;
    }

    /**
     * Unicode 转gbk
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String toGBKWithUTF8(String string) throws UnsupportedEncodingException {
       return chageCharset(string,CharsetUtil.ISO_8859_1,CharsetUtil.GBK);
    }

    /**
     * gbk转unicode 编码
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String toUnicodeWithGBK(String string) throws UnsupportedEncodingException {
       return chageCharset(string,CharsetUtil.GBK,CharsetUtil.ISO_8859_1);
    }



}
