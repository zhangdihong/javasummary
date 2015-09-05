package com.zhuanleme.util;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: FilePathUtil.java</p>
 * <p/>
 * <p>Description: FilePathUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/9/5
 */
public class FilePathUtil {

    /**
     * 判断是否是合法的文件路劲
     * @param path 需要处理的文件路径
     * @return
     */
    public static boolean legalFile(String path){
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatcher(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     * @param file 需要处理的文件路径
     * @return
     * Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public static String commandPath(String file){
        return file.replaceAll("\\\\","/").replaceAll("//",",");
    }
}
