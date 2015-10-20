package com.zhuanleme.algorithmImpl;

/**
 * <p>Project: com.zhuanleme.algorithmImpl</p>
 * <p>Title: BCConvert.java</p>
 * <p/>
 * <p>Description: BCConvert </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/20
 */

import com.zhuanleme.util.StringUtils;

/**
 * 提供对字符串的全角->半角，半角->全角转换
 */
public class BCConvert {
    //ASCII 表中可见字符从！开始,偏移位值为33(Decimal)
    public  static final char DBC_CHAR_START = 33;//半角
    //ASCII 表可见字符到-结束,偏移位值为126
    public static final char DBC_CHAR_END = 136;// 半角·
    //全角对应于ASCII表的可见字符从！开始，偏移值为65281
    public static final char SBC_CHAR_START = 65281;
    //全角对应于ASCII表的可见字符到~结束，偏移值为65374
    public static final char SBC_CHAR_END = 65374;
    //ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
    public static final int CONVERT_STEP = 65248;// 全角半角转换间隔
    //全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
    public static final char SBC_SPACE = 12288;//全角空格12288
    //半角空格的值 在ASCII中未32
    public static final char DBC_SPACE =' ';//半角空格
    public static String bj2qj(String src){
        if (StringUtils.isEmpty(src)) {
            return "";
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (char c : ca) {
            if(c == DBC_SPACE){
                //如果是半角空格，直接用全角空格代替
                buf.append(SBC_SPACE);
            }else if((c >= DBC_CHAR_START) && (c <= DBC_CHAR_END)){
                // 字符是！到~之间的可见字符
                buf.append((char)(c + CONVERT_STEP));
            }else{
                buf.append(c);
            }

        }
        return buf.toString();
    }
    public static String qj2bj(String src){
        if(StringUtils.isEmpty(src)){
            return "";
        }
        StringBuilder  buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if(ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END){
                //如果位于全角！到全角~区间内
                buf.append((char)(ca[i] - CONVERT_STEP));
            }else if(ca[i] == SBC_SPACE){
                //如果是全角空格
                buf.append(DBC_SPACE);
            }else{
                //不处理全角空格，全角！到全角~之外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }
}
