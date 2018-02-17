package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * Created by yuanjx
 * on 2017/8/16
 */
public class FileStorageEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7163144887110206594L;

    private String group;

    private String path;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileStorageEntity{" +
                "group='" + group + '\'' +
                ", path='" + path + '\'' +
                '}' + super.toString();
    }
}
