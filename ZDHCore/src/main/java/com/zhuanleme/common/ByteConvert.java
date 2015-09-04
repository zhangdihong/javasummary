package com.zhuanleme.common;

/**
 * <p>Project: com.zhuanleme.common</p>
 * <p>Title: ByteConvert.java</p>
 * <p/>
 * <p>Description: ByteConvert </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * @author zhangdihong
 * @version 1.0
 * @date 2015/8/31
 */
public class ByteConvert {

    private static final String ten2Any(long num, int base) {
        StringBuilder sb = new StringBuilder(7);
        while (num != 0) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (num % base)));
            num /= base;
        }
        return sb.reverse().toString();
    }
}
