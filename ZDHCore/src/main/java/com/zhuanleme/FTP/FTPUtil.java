package com.zhuanleme.FTP;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FTPUtil.java</p>
 * <p/>
 * <p>Description: FTPUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/22
 */
public interface FTPUtil {


    //  判断远程地址是否存在
    public boolean isExists(String fileName);

    // 下载远程文件
    public boolean downLoad(String fileName);

    // 下载远程目录
    public boolean downLoadDir(String directory);

    //删除远程文件
    public boolean deleteFile(String fileName);

    //删除远程目录
    public boolean deleteDir(String directory);

    // 上传本地文件到远程目录
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete);

    // 上传本地文件到远程目录
    public boolean putFile(File file, String remoteFileName, boolean isDelete);

    // 上传本地目录到远程
    public boolean putDir(String fileName, String remoteFileName, boolean isDelete);

    // 上传本地目录到远程
    public boolean putDir(File file, String remoteFileName, boolean isDelete);

    // 上传本地目录到远程

    public boolean putDir(String fileName, String remoteFileName);

    // 上传本地目录到远程

    public boolean putDir(File file, String remoteFileName);

    // 创建文件夹

    public boolean mkDir(String fileName);

    // 获取远程文件列表
    public List<String> listFile(String directory);

    // 获取远程文件夹的目录结构
    public LinkedList<String> listDir(String directory);

    // 获取远程文件属性以Map形式返回
    public Map<String,FTPAttr> listFileAtrr(String directory);

    //改变FTP连接的工作目录
    public boolean changeWorkDir(String directory);

    // 重命名文件
    public boolean changeName(String oldName, String newName);

    // 返回FTPClient对象(已经打开连接)
    public FTPClient client();

    //释放所有资源
    public void destory();


}
