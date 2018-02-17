package com.clozeblur.re.aws.fdfs.module.query;

/**
 * Image Metadata 查询
 * Created by yuanjx on 2017/12/15.
 */
public class ImageMetadataQuery extends AbstractQuery{

    private Long fileId;
    private Long generateKey;
    private String virtualPath;

    @Override
    public String toString() {
        return "ImageMetadataQuery{" +
                "fileId=" + fileId +
                ", generateKey=" + generateKey +
                ", virtualPath='" + virtualPath + '\'' +
                "} " + super.toString();
    }

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
}
