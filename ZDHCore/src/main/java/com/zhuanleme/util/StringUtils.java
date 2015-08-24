package com.zhuanleme.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: StringUtils.java</p>
 * <p/>
 * <p>Description: 提供常用字符串相关的工具方法 </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/7/31
 */
public class StringUtils {

    /**
     * 判断一个字符串是否为null或"" return true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * @param list   list集合 string类型
     * @param symbol 字符分割符号
     * @return 返回处理后的字符
     */
    public static String joinString(List list, String symbol) {
        String result = "";
        if (null != list) {
            for (Object o : list) {
                String temp = o.toString();
                if (temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 比较str1的值是否存在于str2中
     *
     * @param str1 字符串
     * @param str2 字符串数组(","分隔)
     * @return
     */
    public static boolean reEquals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replace("\\s*", "");
            String[] strings = str2.split(",");
            for (String temp : strings) {
                if (temp.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判读一个值是否存在于一个字符串中
     *
     * @param str1
     * @param str2
     * @param split
     * @return
     */
    public static boolean reEquals(String str1, String str2, String split) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arrStr = str2.split(split);
            for (String temp : arrStr) {
                if (temp.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 截取字符串超过部分用...表示
     *
     * @param str  截取字符串
     * @param size 截取长度
     * @return 返回截取后的字符串
     */
    public static String substringLimit(String str, int size) {
        if (str != null && str.length() > size) {
            str = str.substring(0, size) + "...";
        }
        return str;
    }

    /**
     * 截取字符串超过部分用指定符号表示
     *
     * @param str    截取字符串
     * @param size   截取大小
     * @param symBol 截取符号
     * @return 返回截取后的字符串
     */
    public static String subsringLimitBySymbol(String str, int size, String symBol) {
        if (str != null && str.length() > size && ValidUtil.isValid(symBol)) {
            str = str.substring(0, size) + symBol;
        }
        return str;
    }

    /**
     * 数组转化成指定符号标记字符串
     *
     * @param strArr 字符串数组
     * @param symbol 连接符号
     * @return
     */
    public static String stringJoinBySymbol(String[] strArr, String symbol) {
        String result = "";
        if (null != strArr) {
            for (String temp : strArr) {
                result += temp + symbol;
            }
            if (strArr.length > 1 && ValidUtil.isValid(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }

    /**
     * 隐藏email地址钱
     *
     * @param email eamil地址
     * @return
     */
    public static String getHideEmailPrefix(String email) {
        if (ValidUtil.isValid(email)) {
            int index = email.lastIndexOf("@");
            if (index > 0) {
                email = repeat("*", index);
            }
        }
        return email;
    }

    /**
     * 生成重复字符串
     *
     * @param src 源字符串
     * @param num 重复次数
     * @return
     */
    public static String repeat(String src, int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(src);
        }
        return stringBuilder.toString();
    }

    /**
     * 截取字符串右侧第0位到第Num位
     *
     * @param str 处理的字符串
     * @param num 截取的位置
     * @return 截取后的字符串
     */
    public static String rtrim(String str, int num) {
        if (ValidUtil.isValid(str) && str.length() > num) {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }

    /**
     * 指定字符串转化成list集合
     *
     * @param src
     * @param pattern
     * @return
     */
    public static List<String> parseString2List(String src, String pattern) {
        List<String> stringList = new ArrayList<>();
        if (ValidUtil.isValid(src) && ValidUtil.isValid(pattern)) {
            String[] tt = src.split(pattern);
            stringList.addAll(Arrays.asList(tt));
        }
        return stringList;
    }

    /**
     * 格式化double数据为一个数据格式
     *
     * @param d      double数据
     * @param format 要格式化的字符串 0.0,#.#,#.000
     * @return
     */
    public static String formatDouble(Double d, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(format);
    }

    /**
     * 左边截取指定长度字符串
     *
     * @param src   需要被截取的字符串
     * @param count 截取的长度
     * @return 返回截取后的字符串
     */
    public static String leftSplit(String src, int count) {
        if (ValidUtil.isValid(src)) {
            return "";
        }
        count = (count > src.length()) ? src.length() : count;
        return src.substring(0, count);
    }

    /**
     * 右边截取指定长度字符串
     *
     * @param src   需要被截取的字符串
     * @param count 截取的长度
     * @return 返回截取后的字符串
     */
    public static String rightSplit(String src, int count) {
        if (ValidUtil.isValid(src)) {
            return "";
        }
        count = (count > src.length()) ? src.length() : count;
        return src.substring(src.length() - count, src.length());
    }

    /**
     * 页面中去除字符串的空格、回车、换行符、制表符
     *
     * @param str 需要处理的字符串
     * @return 返回处理后的字符串
     */
    public static String replaceBlank(String str) {
        if (null != str) {
            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = pattern.matcher(str);
            str = matcher.replaceAll("");
        }
        return str;
    }

    /**
     * 字符串是否存在数组中
     *
     * @param str    字符串
     * @param source 数据数组
     * @return true, false
     */
    public static boolean strInArray(String str, String[] source) {
        if (ValidUtil.isValid(str) && ValidUtil.isValid(source)) {
            Arrays.sort(source);
            int index = Arrays.binarySearch(source, str);
            return index != -1;
        }
        return false;
    }

    /**
     * 字符串转换unicode，实现native2ascii.exe类似的功能
     *
     * @param str 需转换的字符串
     * @return 转换后的字符串
     */
    public static String string2Unicode(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            temp = "\\u" + String.valueOf(Integer.toHexString(str.charAt(i)));
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    /**
     * unicode 字符串转换成 字符串
     *
     * @param unicode 需要处理的字符串
     * @return 返回字符串
     */
    public static String unicode2String(String unicode) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            stringBuilder.append((char) data);
        }
        return stringBuilder.toString();
    }

    /**
     * 清除标点符号
     *
     * @param string
     * @return
     */
    public static String trimPunct(String string) {
        if (ValidUtil.isValid(string)) {
            string = string.replaceAll("[\\pP\\p{Punct}]", "");
        }
        return string;
    }

    /**
     * 查看字符串在string中出现的次数
     *
     * @param string 处理的字符串
     * @param str    子字符串
     * @return
     */
    public static int countSubStr(String string, String str) {
        if ((null == str) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }

    /**
     * 移除字符串中的子串
     *
     * @param string
     * @param sub
     * @return
     */
    public static String remove(String string, String sub) {
        int c = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return string;
        }
        int i = string.indexOf(sub, c);
        if (i == -1) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder(string.length());
        do {
            stringBuilder.append(string.substring(c, i));
            c = i + sublen;
        } while ((i = string.indexOf(sub, c)) != -1);
        return stringBuilder.toString();
    }

    /**
     * 转化字符串首字母为大写
     * @param str
     * @return
     */
    public static String upperFirstChar(String str) {
        if (ValidUtil.isValid(str)) {
            return "";
        }
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= (char) 0x20;
        }
        return String.valueOf(chars);
    }

    /**
     * 将字符串首字母转小写
     * @param str
     * @return
     */
    public static String lowerFirstChar(String str){
        if (ValidUtil.isValid(str)) {
            return "";
        }
        char[] chars = str.toCharArray();
        if ((chars[0] >= 'A') && (chars[0] <= 'z')) {
            chars[0] += (char)0x20;
        }
        return String.valueOf(chars);
    }

}
