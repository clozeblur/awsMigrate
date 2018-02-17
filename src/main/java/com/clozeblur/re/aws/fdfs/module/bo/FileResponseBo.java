package com.clozeblur.re.aws.fdfs.module.bo;

import java.util.Date;

/**
 * 文件类型返回response类
 * Created by yuanjx on 2017/12/14.
 */
public class FileResponseBo extends BaseBo {
    private byte[] bytes;
    private String fileName;
    private Date lastModified;
    private String ext;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
