package com.zhuanleme.FTP;

import com.zhuanleme.util.FileUtil;
import com.zhuanleme.util.ValidUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FTPUtilImpl.java</p>
 * <p/>
 * <p>Description: FTPUtilImpl </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/22
 */
public class FTPUtilImpl implements FTPUtil {

    private FTPVo ftpVo;
    private FTPClient ftpClient;

    public FTPUtilImpl(FTPVo ftpVo) {
        this.ftpVo = ftpVo;
    }

    @Override
    public boolean isExists(String fileName) {
        List<String> stringList = listFile(ftpVo.getRemoteDir());
        if (stringList.contains(fileName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean downLoad(String fileName) {
        String localFileName = ftpVo.getLocalDir() + File.separator + fileName;
        FileUtil.createFiles(localFileName);
        OutputStream os = null;
        try {
            os = new FileOutputStream(localFileName, true);
            client().retrieveFile(new String(fileName.getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean downLoadDir(String directory) {
        List<String> files = listFile(directory);
        for (String file : files) {
            downLoad(file);
        }
        return true;
    }

    @Override
    public boolean deleteFile(String fileName) {
        if (isExists(fileName)) {
            try {
                ftpClient.deleteFile(new String(fileName.getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"));
                return reply("DELETE", "", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteDir(String directory) {
        List<String> files = listFile(directory);
        try {
            for (String file : files) {
                deleteFile(file);
            }
            List<String> dirs = listDir(directory);
            for (int i = dirs.size(); i >= 0; i--) {
                ftpClient.removeDirectory(new String(dirs.get(i).getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"));
            }
            ftpClient.removeDirectory(new String(directory.getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply("DELETE", "", directory);
    }

    @Override
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete) {
        File file = new File(fileName);
        return putFile(file, remoteFileName, isDelete);
    }

    @Override
    public boolean putFile(File file, String remoteFileName, boolean isDelete) {
        String fileName = remoteFileName;
        String path = "";
        String parent = getParentPath(fileName);
        if (remoteFileName.lastIndexOf("/") != -1) {
            int index = remoteFileName.lastIndexOf("/");
            path = remoteFileName.substring(0, index);
            fileName = remoteFileName.substring(index + 1);
            mkDir(path);
            changeWorkDir(path);
        }
        try (InputStream inputStream = new FileInputStream(file);) {
            if (isDelete) {
                deleteFile(new String(file.getName().getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"));
            }
            ftpClient.appendFile(new String(fileName.getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1"), inputStream);
            return reply("UPLOAD", file.getAbsolutePath().toString(), remoteFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean putDir(String fileName, String remoteFileName) {
        File file = new File(fileName);
        return putDir(file, remoteFileName);
    }

    @Override
    public boolean putDir(File file, String remoteFileName) {
        List<File> fileList = FileUtil.listFile(file);
        for (File f : fileList) {
            String name = f.getAbsolutePath();
            name = name.substring(name.indexOf(file.getName())).replaceAll("\\\\", "/");
            putFile(f, remoteFileName + "/" + name, true);
        }
        return true;
    }

    @Override
    public boolean mkDir(String directory) {
        directory = directory.replaceAll("//", "/");
        if (directory.startsWith("/")) {
            directory = directory.substring(1);
        }
        if(directory.endsWith("/")){
            directory = directory.substring(0, directory.length() -1 );
        }
        try {
            String[] strs = (new String(directory.getBytes(ftpVo.getRemoteEncoding()), "ISO-8859-1")).split("/");
            String t = "";
            String parent = "";
            for (int i = 0; i < strs.length; i++) {
                t += ("/" + strs[i]);
                if (!isExists(t.substring(1))) {
                    ftpClient.makeDirectory(strs[i]);
                }
                ftpClient.changeWorkingDirectory(strs[i]);
                parent += "../";
            }
            if(strs.length >= 1){
                ftpClient.changeWorkingDirectory(parent);
            }
            return  true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> listFile(String directory) {
        List<String> stringList = new ArrayList<>();
        try {
            FTPFile[] ftpFiles = client().listFiles(directory);
            for (int i = 0; i < ftpFiles.length; i++) {
                String temp = (directory + "/" + ftpFiles[i].getName()).replaceAll("//", "/");
                if (ftpFiles[i].isFile()) {
                    stringList.add(temp);
                } else if (ftpFiles[i].isDirectory()) {
                    stringList.addAll(listFile((temp + "/").replaceAll("//", "/")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    @Override
    public LinkedList<String> listDir(String directory) {
        LinkedList<String> linkedList = new LinkedList<>();
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(directory);
            for (int i = 0; i < ftpFiles.length; i++) {
                String t = (directory + "/" + ftpFiles[i].getName().replaceAll("//", "/"));
                if (ftpFiles[i].isDirectory()) {
                    linkedList.add(t);
                    linkedList.addAll(listDir(t + "/"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkedList;
    }

    @Override
    public Map<String, FileAttr> listFileAtrr(String directory) {
        Map<String, FileAttr> faMap = new HashMap<>();
        try {
            FTPFile[] f = ftpClient.listFiles(directory);
            for (int i = 0; i < f.length; i++) {
                FTPFile file = f[i];
                if (file.isFile()) {
                    String fileName = directory + file.getName();
                    FileAttr fa = new FileAttr();
                    fa.setFileName(fileName);
                    fa.setSize(file.getSize());
                    fa.setModifyTime(file.getTimestamp().getTime());
                    faMap.put(fileName, fa);
                } else if (file.isDirectory()) {
                    faMap.putAll(listFileAtrr(directory + file.getName() + "/"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return faMap;
    }

    @Override
    public boolean changeWorkDir(String directory) {
        try {
            ftpClient.cwd(directory);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeName(String oldName, String newName) {
        return false;
    }

    @Override
    public FTPClient client() {
        return ftpClient;
    }

    @Override
    public void destory() {
        if (ValidUtil.isValid(ftpClient)) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通过FTP状态码判断是否操作成功
    private boolean reply(String operation) {
        int replyCode = client().getReplyCode();
        FTPLog ftpLog = new FTPLog();
        ftpLog.setHost(ftpVo.getHostName());
        ftpLog.setOperation(operation);
        ftpLog.setLocalFile("");
        ftpLog.setRemoteFile("");
        ftpLog.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        return FTPReply.isPositiveCompletion(replyCode);
    }

    private boolean reply(String operation, String localFile, String remoteFile) {
        int replyCode = client().getReplyCode();
        FTPLog ftpLog = new FTPLog();
        ftpLog.setRemoteFile(remoteFile);
        ftpLog.setLocalFile(localFile);
        ftpLog.setHost(ftpVo.getHostName());
        ftpLog.setReplayCode(replyCode);
        ftpLog.setOperation(operation);
        ftpLog.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        return FTPReply.isPositiveCompletion(replyCode);
    }

    private String getParentPath(String file) {
        if (file.indexOf("/") != -1) {
            String temp = null;
            Pattern p = Pattern.compile("[/]+");
            Matcher m = p.matcher(file);
            int i = 0;
            while (m.find()) {
                temp = m.group(0);
                i += temp.length();
            }
            String parent = "";
            for (int j = 0; j < i; j++) {
                parent += "../";
            }
            return parent;
        } else {
            return "./";
        }
    }
}
