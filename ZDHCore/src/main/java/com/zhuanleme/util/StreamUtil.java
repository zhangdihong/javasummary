package com.zhuanleme.util;

import java.io.*;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: StreamUtil.java</p>
 * <p/>
 * <p>Description: StreamUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/8/11
 */
public class StreamUtil {

    /**
     * 输入流转换成字符串
     *
     * @param inputStream 输入流对象
     * @return
     * @throws IOException
     */
    public static String stream2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = new byte[4096];
        for (int n; (n = inputStream.read(bytes)) != -1; ) {
            stringBuilder.append(new String(bytes, 0, n));
        }
        return stringBuilder.toString();
    }

    /**
     * 输入流转换为byte数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] stream2Byte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            baos.write(bytes, 0, len);
        }
        return baos.toByteArray();
    }
//TODO 需思考 available(), while  loop 时在多线程情况会出现资源争夺情况 待测试

    /**
     * 另外一种输入流转byte字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] inputStream2Byte(InputStream inputStream) throws IOException {
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        return bytes;
    }

    /**
     * 将字节流转换为输入流
     *
     * @param bytes
     * @return
     */
    public static InputStream byte2inputStream(byte[] bytes) {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;
    }

    /**
     * 将流对象转化成文件
     * @param inputStream
     * @param outFile
     */
    public static void streamSaveASFile(InputStream inputStream, File outFile) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) == -1) {
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
