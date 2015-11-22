package com.zhuanleme.FTP;

import com.zhuanleme.util.DateUtil;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FTPLog.java</p>
 * <p/>
 * <p>Description: FTPLog </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/17
 */
public class FTPLog {

    private String host;
    private String operation;
    private int replayCode;
    private String localFile;
    private String remoteFile;
    private String replyCodeDesc;
    private String creatTime = DateUtil.DateTime();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getReplayCode() {
        return replayCode;
    }

    public void setReplayCode(int replayCode) {
        this.replayCode = replayCode;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    public String getReplyCodeDesc() {
        return replyCodeDesc;
    }

    public void setReplyCodeDesc(String replyCodeDesc) {
        this.replyCodeDesc = replyCodeDesc;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "FTPLog{" +
                "host='" + host + '\'' +
                ", operation='" + operation + '\'' +
                ", replayCode=" + replayCode +
                ", localFile='" + localFile + '\'' +
                ", remoteFile='" + remoteFile + '\'' +
                ", replyCodeDesc='" + replyCodeDesc + '\'' +
                ", creatTime='" + creatTime + '\'' +
                '}';
    }
}
