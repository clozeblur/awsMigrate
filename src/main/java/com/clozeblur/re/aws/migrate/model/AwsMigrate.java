package com.clozeblur.re.aws.migrate.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public class AwsMigrate implements Serializable {

    private static final long serialVersionUID = 5784885774874788034L;
    private Integer id;
    private Integer fileId;
    private Integer type;
    private String path;
    private String changedPath;
    private Integer status;
    private Date updateTime;

    //非数据库字段
    private Integer batchNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChangedPath() {
        return changedPath;
    }

    public void setChangedPath(String changedPath) {
        this.changedPath = changedPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    @Override
    public String toString() {
        return "AwsMigrate{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", type=" + type +
                ", path='" + path + '\'' +
                ", changedPath='" + changedPath + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                '}';
    }
}
