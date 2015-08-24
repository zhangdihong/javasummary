package com.zhuanleme.util;

import evilp0s.algorithmImpl.FileImpl;
import evilp0s.algorithmImpl.FileTypeImpl;

import java.io.*;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 封装了些文件相关的操�?
 */
public class FileUtil {
    /**
     * Buffer的大�?
     */
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;


    /**
     * 获取文件的行�?
     *
     * @param file 统计的文�?
     * @return 文件行数
     */
    public static int countLines(File file) {
        int count = 0;
        try (
                InputStream is = new BufferedInputStream(new FileInputStream(file))
        ) {
            byte[] c = new byte[BUFFER_SIZE];
            int readChars;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file �?要出来的文件
     * @return 包含�?有行的list
     */
    public static List<String> lines(File file) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file     �?要处理的文件
     * @param encoding 指定读取文件的编�?
     * @return 包含�?有行的list
     */
    public static List<String> lines(File file, String encoding) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的指定的行数数据
     *
     * @param file  处理的文�?
     * @param lines �?要读取的行数
     * @return 包含制定行的list
     */
    public static List<String> lines(File file, int lines) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的指定的行数数据
     *
     * @param file     �?要处理的函数
     * @param lines    �?要处理的行还�?
     * @param encoding 指定读取文件的编�?
     * @return 包含制定行的list
     */
    public static List<String> lines(File file, int lines, String encoding) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 在文件末尾追加一�?
     *
     * @param file �?要处理的函数
     * @param str  添加的子字符�?
     * @return 是否成功
     */
    public static boolean appendLine(File file, String str) {
        String lineSeparator = System.getProperty("line.separator", "\n");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(lineSeparator + str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在文件末尾追加一�?
     *
     * @param file     �?要处理的文件
     * @param str      添加的字符串
     * @param encoding 指定写入的编�?
     * @return 是否成功
     */
    public static boolean appendLine(File file, String str, String encoding) {
        String lineSeparator = System.getProperty("line.separator", "\n");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((lineSeparator + str).getBytes(encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字符串写入到文件中
     */
    public static boolean write(File file,String str){
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字符串以追加的方式写入到文件中
     */
    public static boolean writeAppend(File file,String str){
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字符串以制定的编码写入到文件中
     */
    public static boolean write(File file,String str,String encoding){
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.write(str.getBytes(encoding));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字符串以追加的方式以制定的编码写入到文件中
     */
    public static boolean writeAppend(File file,String str,String encoding){
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write(str.getBytes(encoding));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 快�?�清空一个超大的文件
     *
     * @param file �?要处理的文件
     * @return 是否成功
     */
    public static boolean cleanFile(File file) {
        try (
                FileWriter fw = new FileWriter(file)
        ) {
            fw.write("");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件的Mime类型
     *
     * @param file �?要处理的文件
     * @return 返回文件的mime类型
     * @throws IOException
     */
    public static String mimeType(String file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }

    /**
     * 获取文件的类�?
     * <p/>
     * Summary:只利用文件头做判断故不全
     *
     * @param file �?要处理的文件
     * @return 文件类型
     */
    public static String fileType(File file) {
        return FileTypeImpl.getFileType(file);
    }

    /**
     * 获取文件�?后的修改时间
     *
     * @param file �?要处理的文件
     * @return 返回文件的修改时�?
     */
    public static Date modifyTime(File file) {
        return new Date(file.lastModified());
    }

    /**
     * 获取文件的Hash
     *
     * @param file �?要处理的文件
     * @return 返回文件的hash�?
     */
    public static String hash(File file) {
        return SecUtil.FileMD5(file);
    }


    /**
     * 复制文件
     *
     * @param resourcePath 源文�?
     * @param targetPath   目标文件
     * @return 是否成功
     */
    public static boolean copy(String resourcePath, String targetPath) {
        File file = new File(resourcePath);
        return copy(file, targetPath);
    }

    /**
     * 复制文件
     * 通过该方式复制文件文件越大�?�度越是明显
     *
     * @param file       �?要处理的文件
     * @param targetFile 目标文件
     * @return 是否成功
     */
    public static boolean copy(File file, String targetFile) {
        try (
                FileInputStream fin = new FileInputStream(file);
                FileOutputStream fout = new FileOutputStream(new File(targetFile))
        ) {
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            //设定缓冲�?
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                //准备写入，防止其他读取，锁住文件
                buffer.flip();
                out.write(buffer);
                //准备读取。将缓冲区清理完毕，移动文件内部指针
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件的编�?(cpDetector)探测
     *
     * @param file �?要处理的文件
     * @return 文件的编�?
     */
    public static String cpdetector(File file) {
        try {
            return FileImpl.cpdetector(file.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用�?单的文件头字节特征探测文件编�?
     *
     * @param file �?要处理的文件
     * @return UTF-8 Unicode UTF-16BE GBK
     */
    public static String simpleEncoding(String file) {
        try {
            return FileImpl.simpleEncoding(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建多级目录
     *
     * @param paths �?要创建的目录
     * @return 是否成功
     */
    public static boolean createPaths(String paths) {
        File dir = new File(paths);
        return !dir.exists() && dir.mkdir();
    }

    /**
     * 创建文件支持多级目录
     *
     * @param filePath �?要创建的文件
     * @return 是否成功
     */
    public static boolean createFiles(String filePath) {
        File file = new File(filePath);
        File dir  = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 删除�?个文�?
     *
     * @param file �?要处理的文件
     * @return 是否成功
     */
    public static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除�?个目�?
     *
     * @param file �?要处理的文件
     * @return 是否成功
     */
    public static boolean deleteDir(File file) {
        List<File> files = listFileAll(file);
        if (ValidUtil.isValid(files)) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDir(f);
                } else {
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }


    /**
     * 快�?�的删除超大的文�?
     *
     * @param file �?要处理的文件
     * @return 是否成功
     */
    public static boolean deleteBigFile(File file) {
        return cleanFile(file) && file.delete();
    }

    /**
     * 罗列指定路径下的全部文件
     *
     * @param path �?要处理的文件
     * @return 包含�?有文件的的list
     */
    public static List<File> listFile(String path) {
        File file = new File(path);
        return listFile(file);
    }

    /**
     * 复制目录
     *
     * @param filePath   �?要处理的文件
     * @param targetPath 目标文件
     */
    public static void copyDir(String filePath, String targetPath) {
        File file = new File(filePath);
        copyDir(file, targetPath);
    }

    /**
     * 复制目录
     *
     * @param filePath   �?要处理的文件
     * @param targetPath 目标文件
     */
    public static void copyDir(File filePath, String targetPath) {
        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            createPaths(targetPath);
        }
        File[] files = filePath.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                String path = file.getName();
                if (file.isDirectory()) {
                    copyDir(file, targetPath + "/" + path);
                } else {
                    copy(file, targetPath + "/" + path);
                }
            }
        }
    }

    /**
     * 罗列指定路径下的全部文件
     *
     * @param path �?要处理的文件
     * @return 返回文件列表
     */
    public static List<File> listFile(File path) {
        List<File> list  = new ArrayList<>();
        File[]     files = path.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件�?
     *
     * @param path �?要处理的文件
     * @return 返回文件列表
     */
    public static List<File> listFileAll(File path) {
        List<File> list  = new ArrayList<>();
        File[]     files = path.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                list.add(file);
                if (file.isDirectory()) {
                    list.addAll(listFileAll(file));
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件�?
     *
     * @param path   �?要处理的文件
     * @param filter 处理文件的filter
     * @return 返回文件列表
     */
    public static List<File> listFileFilter(File path, FilenameFilter filter) {
        List<File> list  = new ArrayList<>();
        File[]     files = path.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, filter));
                } else {
                    if (filter.accept(file.getParentFile(), file.getName())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 获取指定目录下的特点文件,通过后缀名过�?
     *
     * @param dirPath  �?要处理的文件
     * @param postfixs 文件后缀
     * @return 返回文件列表
     */
    public static List<File> listFileFilter(File dirPath, final String postfixs) {
        /*
        如果在当前目录中使用Filter讲只罗列当前目录下的文件不会罗列孙子目录下的文件
        FilenameFilter filefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(postfixs);
            }
        };
        */
        List<File> list  = new ArrayList<File>();
        File[]     files = dirPath.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, postfixs));
                } else {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(postfixs.toLowerCase())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 在指定的目录下搜寻文个文�?
     *
     * @param dirPath  搜索的目�?
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     */
    public static List<File> searchFile(File dirPath, String fileName) {
        List<File> list  = new ArrayList<>();
        File[]     files = dirPath.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equals(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 查找符合正则表达式reg的的文件
     *
     * @param dirPath 搜索的目�?
     * @param reg     正则表达�?
     * @return 返回文件列表
     */
    public static List<File> searchFileReg(File dirPath, String reg) {
        List<File> list  = new ArrayList<>();
        File[]     files = dirPath.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, reg));
                } else {
                    String Name = file.getName();
                    if (RegUtil.isMatche(Name, reg)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

}