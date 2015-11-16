package com.zhuanleme.algorithmImpl;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 封装了集中常用的文件读的方法
 */
public class FileReadImpl {

    /**
     * 利用FileChannel直接实现文件的对拷,对于大文件速度特别明显
     * @param source
     * @param target
     */
    public static void copyFileWithChannel(File source,File target){
        try(
                FileInputStream inputStream = new FileInputStream(source);
                FileOutputStream outputStream = new FileOutputStream(target);
                FileChannel in = inputStream.getChannel();
                FileChannel out = outputStream.getChannel();
                ){
            in.transferTo(0, in.size(), out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用FileChangle+buffer缓冲方式拷贝文件
     * @param source
     * @param target
     */
    public static void copyFileWithBuffer(File source, File target){
        try(
                FileInputStream inputStream = new FileInputStream(source);
                FileOutputStream outputStream = new FileOutputStream(target);
                FileChannel in = inputStream.getChannel();
                FileChannel out = outputStream.getChannel();
                ) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while ((in.read(buffer)) != -1 ){
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用buffer实现文件的读取
     * @param source
     * @param target
     */
    public static void customBufferBufferedStreamCopy(File source, File target){
            try (
                    InputStream inputStream = new BufferedInputStream(new FileInputStream(source));
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(target));
                    ){
                    byte[] buffer = new byte[4096];
                    int i;
                    while ((i = inputStream.read(buffer)) != -1){
                        outputStream.write(buffer, 0, i);
                    }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 非缓冲流拷贝文件
     * @param source
     * @param target
     */
    public  static void customStreamCopy(File source, File target){
        try(
                InputStream inputStream = new FileInputStream(source);
                OutputStream outputStream = new FileOutputStream(target);
                ){
            int len;
            byte[] bytes = new byte[4096];
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
