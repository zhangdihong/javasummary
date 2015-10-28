package com.zhuanleme.algorithmImpl;

/**
 * <p>Project: com.zhuanleme.algorithmImpl</p>
 * <p>Title: FileImpl.java</p>
 * <p/>
 * <p>Description: FileImpl </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/21
 */

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 文件相关算法实现
 */
public class FileImpl {


    private static boolean found = false;
    /**
     * 如果完全匹配某个字符集检查算法，则该树形保存该字符集的名称。否则（如二进制文件）其值就为默认值 null，这时应当查询属性
     */
    private static String encoding = null;

    /**
     * 利用文件头特征判断文件的编码方式
     * @param fileName
     * @return
     */
    public static String simpleEncoding(String fileName){
        int p = 0;
        try(
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
                ) {
                p = (bufferedInputStream.read() << 8) + bufferedInputStream.read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String code  = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }
    public static String cpdetector(URL url){
        Charset charset = null;
        try{
            charset = CpDetector.codepageDetectorProxy.detectCodepage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != charset) {
            return charset.name();
        } else {
            return null;
        }

    }

    /**
     * 查看输入字符串编码
     * @param string 字符串
     * @return
     */
    public  static String encoding(String string){
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        Charset charset = null;
        try {
             charset = CpDetector.codepageDetectorProxy.detectCodepage(inputStream,3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null != charset){
            return charset.name();
        }else {
            return null;
        }
    }

//    public static String guestFileEncoding(File file){
//
//    }

    /**
     * 获取文件编码
     * @param file
     * @param ndt
     * @return
     */
    public static String guestFileEncoding(File file, nsDetector ndt){
        ndt.Init(new nsICharsetDetectionObserver() {

            @Override
            public void Notify(String charset) {
                found = true;
                encoding = charset;
            }
        });
        byte[] bytes = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscill = true;
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                ){
            while ((len = bufferedInputStream.read(bytes, 0, bytes.length)) != -1){
                if(isAscill){
                    isAscill = ndt.isAscii(bytes, len);
                }
                if(!isAscill && !done){
                    done = ndt.DoIt(bytes, len,false);
                }

            }
            ndt.DataEnd();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isAscill){
            found = true;
            encoding = "ASCII";
        }
        if(!found){
            String prob[] = ndt.getProbableCharsets();
            if(prob.length > 0){
                return prob[0];
            }else{
                return null;
            }
        }
        return encoding;
    }

}
