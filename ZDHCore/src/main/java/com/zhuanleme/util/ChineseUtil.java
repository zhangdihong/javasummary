package com.zhuanleme.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: ChineseUtil.java</p>
 * <p/>
 * <p>Description: ChineseUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/15
 */
public class ChineseUtil {

    /**
     * 中文转拼音
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
         char[] input = inputString.trim().toCharArray();
        String output = "";
        for (int i = 0; i < input.length; i++) {
            if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i],format);
                    output += temp[0];
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                output += Character.toString(input[i]);
            }
        }
        return output;
    }

    /**
     * 获取汉语拼音的首字母
     * @param chinese
     * @return
     */
    public  static  String getFirstSpell(String chinese){
        StringBuffer stringBuffer = new StringBuffer();
        char[] chineseChar = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < chineseChar.length; i++) {
            if(chineseChar[i] > 128){
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(chineseChar[i], format);
                    if (null != temp) {
                        stringBuffer.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                stringBuffer.append(chineseChar[i]);
            }
        }
        return stringBuffer.toString().replaceAll("\\W","").trim();
    }

    /**
     * 中文转拼音
     * @param chinese
     * @return
     */
    public static String getFullSpell(String chinese){
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = chinese.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] > 128){
                try {
                    stringBuffer.append(PinyinHelper.toHanyuPinyinStringArray(chars[i],format)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                stringBuffer.append(chars[i]);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 判断部分cjk字符(cjk同意汉字)
     * @param chinese
     * @return
     */
    public static boolean  isChineseByREG(String chinese){
        if(null == chinese){
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(chinese.trim()).find();
    }

    /**
     * 判断部分cjk字符(cjk统一字符)
     * @param string
     * @return
     */
    public static boolean isChineseByName(String string){
        if (null == string) {
            return  false;
        }
        //大小写不同：\\p 表示包含, \\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码， \\P{Cn} 就表示 Unicode中已经被定义字符的编码
//        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        String  reg     = "\\p{InCJK_UNIFIED_IDEOGRAPHS}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(string.trim()).find();
    }
    public static boolean isChinese(String strName){
        char[] chars = strName.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(isChinese(c)){
                return true;
            }

        }
        return  false;
    }

    /**
     * 判读是否是中文
     * @param c
     * @return
     */
    public static boolean isChinese(char c){
        Character.UnicodeBlock cub = Character.UnicodeBlock.of(c);
        if (cub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || cub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || cub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || cub == Character.UnicodeBlock.GENERAL_PUNCTUATION || cub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || cub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    public  static int chineseCount(String str){
        Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
        Matcher matcher = p.matcher(str);
        int count = 0;
        while (matcher.find()) {
            String temp = matcher.group(0);
            count += temp.length();
        }
        return count;

    }

    /**
     * 判断是否乱码
     * @param strName
     * @return
     */
    public static boolean isMessCode(String strName){
        Pattern p = Pattern.compile("\\s*|\t*\r*|\n*");
        Matcher matcher = p.matcher(strName);
        String after = matcher.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0f;
        float count = 0f;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if(!Character.isLetterOrDigit(c)){
                if(!ChineseUtil.isChinese(c)){
                    count ++;
                }
                chLength ++;
            }
        }
        float result = count / chLength;
        if(result > 0.4){
            return true;
        }else{
            return false;
        }
    }

}
