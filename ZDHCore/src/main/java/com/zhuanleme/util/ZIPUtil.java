package com.zhuanleme.util;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: ZIPUtil.java</p>
 * <p/>
 * <p>Description: 压缩文档相关的工具类 </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/7/29
 */
public class ZIPUtil {

//    /**
//     * 文档压缩
//     * @param file 需要压缩的文件或目录
//     * @param dest 压缩后的文件名称
//     * @throws Exception
//     */
//    public static void deCompress(File file, String dest) throws Exception {
//        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest))) {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
//        if (inFile.isDirectory()) {
//            File[] files = inFile.listFiles();
//            if (ValidUtil.isValid(files)) {
//                for (File file : files) {
//                    String name = inFile.getName();
//                    if (!"".equals(dir)) {
//                        name = dir + "/" + name;
//                    }
//                    zipFile(file, zos, name);
//                }
//            }
//        } else {
//            String entryName = null;
//            if (!"".equals(dir)) {
//                entryName = dir + "/" + inFile.getName();
//            } else {
//                entryName = inFile.getName();
//            }
//            ZipEntry entry = new ZipEntry(entryName);
//            zos.putNextEntry(entry);
//            try (InputStream is = new FileInputStream(inFile)) {
//                int len = 0;
//                while ((len = is.read()) != -1) {
//                    zos.write(len);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     *文档解压
//     * @param source 需要解压缩的文档名称
//     * @param path 需要接压缩的路径
//     * @throws IOException
//     */
//    public static void unCompress(File source, String path) throws IOException {
//        ZipEntry zipEntry = null;
//        FileUtil.createPaths(path);
//        //实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
//        //实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNexEntry方法依次拿到每一个ZipEntry对象
//        try (
//                ZipFile zipFile = new ZipFile(source);
//                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source));
//        ) {
//            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
//                String fileName = zipEntry.getName();
//                File temp = new File(path + "/" + fileName);
//                if (!temp.getParentFile().exists()) {
//                    temp.getParentFile().mkdirs();
//                }
//                try (
//                        OutputStream os = new FileOutputStream(temp);
//                        InputStream is = zipFile.getInputStream(zipEntry)
//                ) {
//                    int len = 0;
//                    while ((len = is.read()) != -1) {
//                        os.write(len);
//                    }
//                } catch (IOException e) {
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
