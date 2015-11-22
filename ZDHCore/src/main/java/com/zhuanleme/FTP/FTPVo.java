package com.zhuanleme.FTP;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FTPVo.java</p>
 * <p/>
 * <p>Description: FTPVo </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/17
 */
public class FTPVo {

    /**
     * 主机名
     */
    private String hostName;
    private int port;
    private String userName;
    private String passWord;
    private String remoteDir;
    private String localDir;
    private String remoteEncoding;
    private boolean passiveMode;

    public FTPVo(String hostName, int port, String userName, String passWord, String remoteDir, String localDir, String remoteEncoding, boolean passiveMode) {
        this.hostName = hostName;
        this.port = port;
        this.userName = userName;
        this.passWord = passWord;
        this.remoteDir = remoteDir;
        this.localDir = localDir;
        this.remoteEncoding = remoteEncoding;
        this.passiveMode = passiveMode;
    }

}
