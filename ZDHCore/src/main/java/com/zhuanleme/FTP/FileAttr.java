package com.zhuanleme.FTP;

import java.util.Date;

/**
 * <p>Project: com.zhuanleme.FTP</p>
 * <p>Title: FileAttr.java</p>
 * <p/>
 * <p>Description: FileAttr </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/17
 */

/**
 * FTP 文件属性
 */
public class FileAttr {

    private String fileName;
    private Date modifyTime;
    private Long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getModifyTime() {
        return new Date(modifyTime.getTime());
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
