package com.zhuanleme.util;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ��װ���ļ���صĲ���
 */
public class FileUtil {

    /**
     * Buffer�Ĵ�С
     */
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

    /**
     * ��ȡ�ļ�������
     *
     * @param file ͳ�Ƶ��ļ�
     * @return �ļ�����
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
     * ���б�ķ�ʽ��ȡ�ļ���������
     *
     * @param file ��Ҫ�г����ļ�
     * @return ���������е�list
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
     * ���б�ķ�ʽ��ȡ�ļ���������
     *
     * @param file     ��Ҫ������ļ�
     * @param encoding �ƶ���ȡ�ļ��ı���
     * @return ���������е�list
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
     * ���б�ķ�ʽ��ȡ�ļ����ƶ���������
     *
     * @param file  ������ļ�
     * @param lines ��Ҫ��ȡ������
     * @return �����ƶ��е�list
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
     * ���б�ķ�ʽ��ȡ�ļ���ָ������������
     *
     * @param file     ��Ҫ����ĺ���
     * @param lines    ��Ҫ���������
     * @param encoding ָ����ȡ�ļ��ı���
     * @return ����ָ���е�list
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
     * ���ֽڵķ�ʽ��ȡ�ļ�
     * @param file
     * @return
     */
    public static byte[] readAsByte(File file) {
        byte[] res = new byte[0];
        try (FileInputStream fs = new FileInputStream(file);
             FileChannel channel = fs.getChannel()
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0){

            }
            res = byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ���ֽڵķ�ʽ��ȡ�ϴ���ļ�
     * @param file
     * @return
     */
    public static byte[] readAsByteWithBigFile(File file){
        byte[] res = new byte[0];
        try (
                FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
                ){
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0 ,fileChannel.size()).load();
            res = new byte[(int)fileChannel.size()];
            if(byteBuffer.remaining() > 0){
                byteBuffer.get(res, 0, byteBuffer.remaining());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  res;
    }

    /**
     * ���ַ����ķ�ʽ��ȡ�ļ�
     * @param file
     * @param encoding
     * @return
     */
    public static String readAsString(File file,String encoding){
        String res = "";
        try {
            res = new String(readAsByte(file),encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ���ַ������ö�ȡ�ϴ��ļ�
     * @param file
     * @param enconding
     * @return
     */
    public static String readAsStringWithBigFile(File file,String enconding){
        String res = "";
        try {
            res = new String(readAsByteWithBigFile(file),enconding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * ���ļ�ĩβ׷��һ��
     *
     * @param file ��Ҫ����ĺ���
     * @param str  ��ӵ����ַ���
     * @return �Ƿ�ɹ�
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
     * ���ļ�ĩβ׷��һ��
     *
     * @param file     ��Ҫ������ļ�
     * @param str      ��ӵ��ַ���
     * @param encoding ָ��д��ı���
     * @return �Ƿ�ɹ�
     */
    public static boolean appendLine(File file, String str, String encoding) {
        String lineSeparator = System.getProperty("line.separator", "\n");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((lineSeparator + str).getBytes(encoding));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ���ַ���д�뵽�ļ���
     * mode r ֻ�� rw ��д rwd ��д���ұ���Ӳ�� rws
     */
    public static boolean write(File file, String str) {
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
     * ���ַ�����׷�ӵķ�ʽд�뵽�ļ���
     */
    public static boolean writeAppend(File file, String str) {
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
     * ���ַ�����ָ���ı���д�뵽�ļ���
     */
    public static boolean write(File file, String str, String encoding) {
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
     * ���ַ�����׷�ӵķ�ʽ��ָ���ı���д�뵽�ļ���
     */
    public static boolean writeAppend(File file, String str, String encoding) {
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
     * �������һ�������ļ�
     *
     * @param file ��Ҫ������ļ�
     * @return �Ƿ�ɹ�
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
     * ��ȡ�ļ���Mime����
     *
     * @param file ��Ҫ������ļ�
     * @return �����ļ���mime����
     * @throws IOException
     */
    public static String mimeType(String file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }
    /**
     * ��ȡ�ļ�������
     * <p/>
     * Summary:ֻ�����ļ�ͷ���жϹʲ�ȫ
     *
     * @param file ��Ҫ������ļ�
     * @return �ļ�����
     */
//    public static String fileType(File file) {
//        return FileTypeImpl.getFileType(file);
//    }

    /**
     * ��ȡ�ļ������޸�ʱ��
     *
     * @param file ��Ҫ������ļ�
     * @return �����ļ����޸�ʱ��
     */
    public static Date modifyTime(File file) {
        return new Date(file.lastModified());
    }
    /**
     * ��ȡ�ļ���hash
     *
     * @param file ��Ҫ������ļ�
     * @return �����ļ���hashֵ
     */
//    public static String hash(File file) {
//        return SecUtil.FileMD5(file);
//    }

    /**
     * �����ļ�
     * ͨ���÷�ʽ�����ļ�Խ���ٶ�Խ������
     *
     * @param file       ��Ҫ������ļ�
     * @param targetFile Ŀ���ļ�
     * @return �Ƿ�ɹ�
     */
    public static boolean copy(File file, String targetFile) {
        try (
                FileInputStream fin = new FileInputStream(file);
                FileOutputStream fout = new FileOutputStream(new File(targetFile))
        ) {
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            //�趨������
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                //׼��д��,��ֹ������ȡ����ס�ļ�
                buffer.flip();
                out.write(buffer);
                //׼����ȡ����������������ϣ��ƶ��ļ��ڲ�ָ��
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * ��ȡ�ļ��ı���(cpDetector)̽��
     *
     * @param file ��Ҫ������ļ�
     * @return �ļ��ı���
     */
//    public static String cpdetector(File file) {
//        try {
//            return FileImpl.cpdetector(file.toURL());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    /**
     * ���ü򵥵��ļ�ͷ�ֽ�����̽���ļ�����
     *
     * @param file ��Ҫ������ļ�
     * @return UTF-8 Unicode UTF-16BE GBK
     */
//    public static String simpleEncoding(String file) {
//        try {
//            return FileImpl.simpleEncoding(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * �����༶Ŀ¼
     *
     * @param paths ��Ҫ������Ŀ¼
     * @return �Ƿ�ɹ�
     */
    public static boolean createPaths(String paths) {
        File dir = new File(paths);
        return !dir.exists() && dir.mkdir();
    }

    /**
     * �����ļ�֧�ֶ༶Ŀ¼
     *
     * @param filePath ��Ҫ�������ļ�
     * @return �Ƿ�ɹ�
     */
    public static boolean createFiles(String filePath) {
        File file = new File(filePath);
        File dir = file.getParentFile();
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
     * ɾ��һ���ļ�
     *
     * @param file ��Ҫ������ļ�
     * @return �Ƿ�ɹ�
     */
    public static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * ɾ��һ��Ŀ¼
     *
     * @param file ��Ҫ������ļ�
     * @return �Ƿ�ɹ�
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
     * ����ɾ��������ļ�
     *
     * @param file ��Ҫ������ļ�
     * @return �Ƿ�ɹ�
     */
    public static boolean deleteBigFile(File file) {
        return cleanFile(file) && file.delete();
    }

    /**
     * ����ָ��·���µ�ȫ���ļ�
     *
     * @param path ��Ҫ������ļ�
     * @return ���������ļ���list
     */
    public static List<File> listFile(String path) {
        File file = new File(path);
        return listFile(file);
    }

    /**
     * ����Ŀ¼
     *
     * @param filePath   ��Ҫ������ļ�
     * @param targetPath Ŀ���ļ�
     */
    public static void copyDir(String filePath, String targetPath) {
        File file = new File(filePath);
        copyDir(file, targetPath);
    }

    /**
     * ����Ŀ¼
     *
     * @param filePath   ��Ҫ������ļ�
     * @param targetPath Ŀ���ļ�
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
     * ����ָ��·���µ�ȫ���ļ�
     *
     * @param path ��Ҫ������ļ�
     * @return �����ļ��б�
     */
    public static List<File> listFile(File path) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
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
     * ����ָ��·���µ�ȫ���ļ������ļ���
     *
     * @param path ��Ҫ������ļ�
     * @return �����ļ��б�
     */
    public static List<File> listFileAll(File path) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
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
     * ����ָ��·���µ�ȫ���ļ������ļ���
     *
     * @param path   ��Ҫ������ļ�
     * @param filter �����ļ���filter
     * @return �����ļ��б�
     */
    public static List<File> listFileFilter(File path, FilenameFilter filter) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
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
     * ��ȡָ��Ŀ¼�µ��ص��ļ���ͨ����׺������
     *
     * @param dirPath  ��Ҫ������ļ�
     * @param postfixs �ļ���׺
     * @return �����ļ��б�
     */
    public static List<File> listFileFilter(File dirPath, final String postfixs) {
        /*
         ����ڵ�ǰĿ¼��ʹ��Filter��ֻ���е�ǰĿ¼�µ��ļ�������������Ŀ¼�µ��ļ�
        FilenameFilter filefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(postfixs);
            }
        };
        */
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
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
     * ��ָ����Ŀ¼����Ѱĳ���ļ�
     *
     * @param dirPath  ������Ŀ¼
     * @param fileName �������ļ���
     * @return �����ļ��б�
     */
    public static List<File> searchFile(File dirPath, String fileName) {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
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
     * ���ҷ���������ʽ���ļ�
     *
     * @param dirPath ������Ŀ¼
     * @param reg     ������ʽ
     * @return �����ļ��б�
     */
    public static List<File> searchFileReg(File dirPath, String reg) {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (ValidUtil.isValid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, reg));
                } else {
                    String Name = file.getName();
                    if (RegUtil.isMatcher(Name, reg)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }
}