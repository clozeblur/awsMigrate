package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件抽象类, 包含文件记录的基础字段
 * Created by yuanjx on 2017/12/8.
 */
public abstract class FileEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3327088019798123031L;

    @Override
    public String toString() {
        return "FileEntity{" +
                "deleteState=" + deleteState +
                ", version=" + version +
                ", virtualPath='" + virtualPath + '\'' +
                ", size=" + size +
                ", ext='" + ext + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadType=" + uploadType +
                ", remark='" + remark + '\'' +
                ", domain='" + domain + '\'' +
                ", clientInfo='" + clientInfo + '\'' +
                ", uploadTime=" + uploadTime +
                '}' + super.toString();
    }

    /**
     * WEB 上传方式
     */
    public static final int UPLOAD_TYPE_WEB = 1;

    /**
     * Jar 包上传方式
     */
    public static final int UPLOAD_TYPE_JAR = 2;

    /**
     * 删除标记
     */
    protected Integer deleteState;
    /**
     * 版本号
     */
    protected Integer version;

    /**
     * 访问该文件时使用的虚拟路径,不包括文件后缀
     */
    protected String virtualPath;

    /**
     * 文件大小
     */
    protected Integer size;

    /**
     * 文件后缀
     */
    protected String ext;

    /**
     * 原文件名
     */
    protected String fileName;

    /**
     * 文件上传方式
     * 使用 FileDao 中定义的静态变量值
     */
    protected Integer uploadType;

    /**
     * 备注
     * TODO: 该字段含义不清, 需要后续补上
     */
    protected String remark;

    /**
     * 上传文件所属域名
     */
    protected String domain;

    /**
     * 上传客户端信息
     * 包括referrer, ip, agent 信息
     */
    protected String clientInfo;

    /**
     * 上传时间
     */
    protected Date uploadTime;


    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
