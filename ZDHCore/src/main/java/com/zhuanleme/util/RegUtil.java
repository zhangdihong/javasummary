package com.zhuanleme.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: RegUtil.java</p>
 * <p/>
 * <p>Description: RegUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/8/14
 */
public class RegUtil {

    /**
     * 字母数字字符组 [a-zA-Z0-9]
     */
    public static final String REG_ALNUM = "\\p{Alnum}";
    /**
     * 字符串[a-zA-Z]
     */
    public static final String REG_ALPHA = "\\p{Alpha}";
    /**
     * ASCII 字符串
     */
    public static final String REG_ASCII = "\\p{ASCII}";
    /**
     * 空格
     */
    public static final String REG_BLANK = "\\p{Blank}";
    /**
     * 暂时不知道
     */
    public static final String REG_CNTRL = "\\p{Cntrl}";
    /**
     * 数字
     */
    public static final String REG_DIGITS = "\\p{Digit}";
    /**
     * 暂时不知道
     */
    public static final String REG_GRAPH = "\\p{Graph}";
    /**
     * 小写字符
     */
    public static final String REG_LOWER = "\\p{Lower}";
    /**
     * 可见字符包括空格
     */
    public static final String REG_PRINT = "\\p{Print}";
    /**
     * 空格
     */
    public static final String REG_SPACE = "\\p{Space}";
    /**
     * 大写字母
     */
    public static final String REG_UPPER = "\\p{Upper}";
    /**
     * 十六进制数字
     */
    public static final String REG_XDIGIT = "\\p{XDigit}";
    /**
     * 空白行
     */
    public static final String REG_SPACE_LINE = "\\n\\s*\\r*";
    /**
     * 收尾空白字符
     */
    public static final String REG_SPACE_POINT = "^\\s*|\\s*$";
    /**
     * HTML
     */
    public static final String REG_HTML = "<(\\S*?)[^>]*>.*?</\\1>|<./*?/>";
    /**
     * EMAIL
     */
    public static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 国内固定电话
     */
    public static final String REG_FIXED_TELEPHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
    /**
     * 邮政编码
     */
    public static final String REG_POSTALCODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 身份证编码，没有进行区域校验
     */
    public static final String REG_IDENTIFICATION_CARD = "\\d{15}|\\{18}";
    /**
     * 验证URL
     */
    //TODO 验证URL待定
//    public static final String REG_URL  = "/((http|ftp|https|file)://([\w\-]+\.)+[\w\-]+(/[\w\u4e00-\u9fa5\-\./?@\%\!\&=\+\~\:\#\;\,]*)?)/ig";
    /**
     * 移动电话
     */
    public static final String REG_MOBILE_TELEPHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|4|5|6|7|8|9])\\d{8}$";
    /**
     * 合法的名字（字母开头，语序5-16字节，语序字母数字下划线）
     */
    public static final String REG_LEGAL_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";

    /**
     * 判断是否数字表示
     *
     * @param src
     * @return
     */
    public static boolean isNumeric(String src) {
        boolean return_value = false;
        if (ValidUtil.isValid(src)) {
            Pattern pattern = Pattern.compile(REG_DIGITS);
            Matcher matcher = pattern.matcher(src);
            if (matcher.find()){
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断字符串是否符合正则表达式
     *
     * @param str 需要处理的字符串
     * @param reg 正则
     * @return
     */
    public static boolean isMatcher(String str, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 通过正则表达式获取字符串出现的次数
     *
     * @param str
     * @param reg
     * @return
     */
    public static int countString(String str, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        int i = 0;
        while (matcher.find()) {
            i++;
        }
        return i;
    }

    public static boolean isEmail(String email) {
        if (ValidUtil.isValid(email) || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile(REG_EMAIL);
        return pattern.matcher(email).matches();
    }
}
