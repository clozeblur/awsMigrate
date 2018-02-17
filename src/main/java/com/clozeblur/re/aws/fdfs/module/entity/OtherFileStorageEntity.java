package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * OtherFileStorageEntity
 * Created by yuanjx on 2017/12/14.
 */
public class OtherFileStorageEntity extends FileStorageEntity implements Serializable {
    private static final long serialVersionUID = 6363114042247427352L;

    private Integer storageType;
    private Integer deleteState;
    private Integer version;


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
        return "OtherFileStorageEntity{" +
                "storageType=" + storageType +
                ", deleteState=" + deleteState +
                ", version=" + version +
                '}' + super.toString();
    }
}
