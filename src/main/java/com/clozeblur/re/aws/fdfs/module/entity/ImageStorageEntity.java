package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * ImageStorage信息
 * Created by yuanjx on 2017/12/13.
 */
public class ImageStorageEntity extends FileStorageEntity implements Serializable {

    public static final int STORAGE_TYPE_FASTDFS = 2;

    private static final long serialVersionUID = 1547697227263458021L;
    private Long generateKey;
    private Integer storageType;
    private Integer deleteState;
    private Integer version;

    public Long getGenerateKey() {
        return generateKey;
    }

    public void setGenerateKey(Long generateKey) {
        this.generateKey = generateKey;
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }


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

    @Override
    public String toString() {
        return "ImageStorageEntity{" +
                "generateKey=" + generateKey +
                ", storageType=" + storageType +
                ", deleteState=" + deleteState +
                ", version=" + version +
                '}' + super.toString();
    }
}
