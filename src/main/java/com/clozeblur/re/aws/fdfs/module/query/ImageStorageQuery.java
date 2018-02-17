package com.clozeblur.re.aws.fdfs.module.query;

/**
 * 查找图片存放
 * Created by yuanjx on 2017/12/13.
 */
public class ImageStorageQuery extends AbstractQuery{

    private Long fileId;

    private Long generateKey;

    private String virtualPath;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getGenerateKey() {
        return generateKey;
    }

    public void setGenerateKey(Long generateKey) {
        this.generateKey = generateKey;
    }

    @Override
    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    @Override
    public String toString() {
        return "ImageStorageQuery{" +
                "fileId=" + fileId +
                ", generateKey=" + generateKey +
                ", virtualPath='" + virtualPath + '\'' +
                "} ";
    }
}
