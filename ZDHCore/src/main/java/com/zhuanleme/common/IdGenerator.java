package com.zhuanleme.common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Project: com.zhuanleme.common</p>
 * <p>Title: IdGenerator.java</p>
 * <p/>
 * <p>Description: IdGenerator </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * <p>Company: 华炜云商科技有限公司 www.hwtech.cc</p>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/9/4
 */
public class IdGenerator {

    private final static String          str36    = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * 获取24位不重复的字符串，根据时间，机器信息(网卡，cpu等),随机数生成
     * @return
     */
    public static String getId(){
        return new ObjectId().toString();
    }

    /**
     * 获取14位 不重复字符串和24 生成规则不同
     * @return
     */
    public static String get15Id(){
        return create15();
    }

    /**
     * 获取十五位不重复字符串 和24 位生成规则不同
     * @return
     */
    public static String get14Id(){
        return create14();
    }
    /**
     * 创建一个15位的字符串，一毫秒内不超过1600000个内不重复：36*36*36*36。
     *
     * @return
     */
    private final static String create15() {
        StringBuilder sb = new StringBuilder(15);
        sb.append(Long.toHexString(System.currentTimeMillis()));
        String str = longTo36(atomicInteger.incrementAndGet());
        if (str.length() == 1) {
            sb.append("000").append(str);
        } else if (str.length() == 2) {
            sb.append("00").append(str);
        } else if (str.length() == 3) {
            sb.append("0").append(str);
        } else {
            sb.append(str);
        }
        return sb.toString();
    }
    /**
     * long型10进制转换成36进制的字符串形式
     *
     * @param num
     * @return
     */
    private static final String longTo36(long num) {
        return ten2Any(num, 36);
    }
    /**
     * 创建一个14位的字符串，一毫秒内不超过46656个内不重复：36*36*36。
     *
     * @return
     */
    private final static String create14() {
        StringBuilder sb = new StringBuilder(14);
        sb.append(Long.toHexString(System.currentTimeMillis()));
        String str = longTo36(atomicInteger.incrementAndGet());
        if (str.length() == 1) {
            sb.append("00").append(str);
        } else if (str.length() == 2) {
            sb.append("0").append(str);
        } else {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * 10进制转任意进制
     *
     * @param num  Long型值
     * @param base 转换的进制
     * @return 任意进制的字符形式
     */
    private static final String ten2Any(long num, int base) {
        StringBuilder sb = new StringBuilder(7);
        while (num != 0) {
            sb.append(str36.charAt((int) (num % base)));
            num /= base;
        }
        return sb.reverse().toString();
    }
}
