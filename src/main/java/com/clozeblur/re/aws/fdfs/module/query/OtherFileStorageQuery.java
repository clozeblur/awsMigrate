package com.clozeblur.re.aws.fdfs.module.query;

/**
 * OtherFileStorageQuery
 * Created by yuanjx on 2017/12/14.
 */
public class OtherFileStorageQuery extends AbstractQuery {

    private String virtualPath;

    private Long fileId;

    @Override
    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
