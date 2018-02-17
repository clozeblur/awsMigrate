package com.clozeblur.re.aws.fdfs.module.context;

/**
 * 获取文件上下文
 * Created by yuanjx on 2017/12/14.
 */
public class FetchFileContext {

    /**
     * file id
     */
    private Long fileId;

    /**
     * virtualPath
     */
    private String virtualPath;

    /**
     * 扩展名
     */
    private String ext;

    /**
     * 上传文件原来的完整名
     */
    private String fileName;

    /**
     * 上传文件原domain
     */
    private String domain;

    @Override
    public String toString() {
        return "FetchFileContext{" +
                "fileId=" + fileId +
                ", virtualPath='" + virtualPath + '\'' +
                ", ext='" + ext + '\'' +
                ", fileName='" + fileName + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
