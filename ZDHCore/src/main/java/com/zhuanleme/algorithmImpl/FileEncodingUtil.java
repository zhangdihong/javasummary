package com.zhuanleme.algorithmImpl;
/**
 * <p>Project: com.zhuanleme.algorithmImpl</p>
 * <p>Title: FileEncodingUtil.java</p>
 * <p/>
 * <p>Description: FileEncodingUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/21
 */

import com.zhuanleme.util.FileUtil;
import com.zhuanleme.util.ValidUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

/**
 * 文件编码相关的一些工具
 */
public class FileEncodingUtil {

    /**
     * 读取文件从指定编码写入到指定编码
     * @param file 文件
     * @param fromCharsetName  读取编码
     * @param toCharsetName  写入编码
     * @param filenameFilter 文件过滤
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName, FilenameFilter filenameFilter) {
        if (file.isDirectory()) {
            List<File> list = ValidUtil.isValid(filenameFilter) ? FileUtil.listFileFilter(file, filenameFilter) : FileUtil.listFile(file);
            if (ValidUtil.isValid(list)) {
                for (File f : list) {
                    convert(f, fromCharsetName, toCharsetName, filenameFilter);
                }
            }
        } else {
            if (null == filenameFilter || filenameFilter.accept(file.getParentFile(), file.getName())) {
                String fileCcntent = getFileContentFromCharset(file, fromCharsetName);
                saveFile2Charset(file, toCharsetName, fileCcntent);
            }
        }
    }

    /**
     * 以指定编码读取文件返回读取内容
     *
     * @param file
     * @param fromCharsetName
     * @return
     */
    public static String getFileContentFromCharset(File file, String fromCharsetName) {
        String str = "";
        if (!Charset.isSupported(fromCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        try (
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, fromCharsetName);
        ) {
            char[] c = new char[(int) file.length()];
            inputStreamReader.read(c);
            str = new String(c).trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 存在就覆盖不存在就创建
     *
     * @param file
     * @param charsetName
     * @param content
     */
    public static void saveFile2Charset(File file, String charsetName, String content) {
        if (!Charset.isSupported(charsetName)) {
            throw new UnsupportedCharsetException(charsetName);
        }
        try (
                OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charsetName);
        ) {
            outputStreamWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
