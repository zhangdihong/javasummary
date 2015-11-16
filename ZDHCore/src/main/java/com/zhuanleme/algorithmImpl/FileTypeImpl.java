package com.zhuanleme.algorithmImpl;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Project: com.zhuanleme.algorithmImpl</p>
 * <p>Title: FileTypeImpl.java</p>
 * <p/>
 * <p>Description: FileTypeImpl </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/29
 */
// 利用文件头实现一些文件类型的方法封装
public class FileTypeImpl {

    public final static Map<String,String> FILE_TYPE_MAP = new HashMap<>();
    static {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); //JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); //PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638"); //GIF (gif)
        FILE_TYPE_MAP.put("tif", "49492A00"); //TIFF (tif)
        FILE_TYPE_MAP.put("bmp", "424D"); //windows Bitmap（bmp）
        FILE_TYPE_MAP.put("dwg", "41433130"); //CAD (dwg)
        FILE_TYPE_MAP.put("html", "68746D6C3E"); //HTML (html)
        FILE_TYPE_MAP.put("rtf", "7B5C727466"); //Rich Text Format  (rtf)
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053"); //psd
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); //Email
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); //outlook Expresss
        FILE_TYPE_MAP.put("pst", "2142444E"); //outlook pst
        FILE_TYPE_MAP.put("xls", "D0CF11E0"); //MS word
        FILE_TYPE_MAP.put("doc", "D0CF11E0"); //MS excel  word 文件头信息一样
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); //MS access
        FILE_TYPE_MAP.put("wpd", "FF575043"); //wordPerfect
        FILE_TYPE_MAP.put("ps", "25215032D"); // ps
        FILE_TYPE_MAP.put("pdf", "255044462D312E"); //JPEG (jpg)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); //JPEG (jpg)
        FILE_TYPE_MAP.put("pwl", "E3828596"); //JPEG (jpg)
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD"); // real audio
        FILE_TYPE_MAP.put("rm", "2E524D46"); //real media
        FILE_TYPE_MAP.put("mpg", "000001BA");
        FILE_TYPE_MAP.put("mov", "6"); //wave
        FILE_TYPE_MAP.put("mov", "6D6F6F76"); //Quicktime
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); //windows media
        FILE_TYPE_MAP.put("mid", "4DS546864"); //MIDI
    }

    public final static String getFileType(File file){
        String fileType = null;
        byte[] bytes = new byte[50];
        try(
               InputStream inputStream = new FileInputStream(file);
                ) {
                inputStream.read(bytes);
                fileType = getFileTypeByStream(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileType;
    }
    public final static String getFileTypeByStream(byte[] bytes){
        String fileTypeHex = String.valueOf(getFileHexString(bytes));
        Iterator<Map.Entry<String,String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
        while(entryiterator.hasNext()){
            Map.Entry<String,String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue();
            if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)){
                return entry.getKey();
            }
        }
        return null;
    }
    /**
     * 获取文件hexString
     * @param bytes
     * @return
     */
    public final static String getFileHexString(byte[] bytes){
        StringBuilder  stringBuilder = new StringBuilder();
        if(bytes.length <= 0){
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if(hv.length() < 2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
