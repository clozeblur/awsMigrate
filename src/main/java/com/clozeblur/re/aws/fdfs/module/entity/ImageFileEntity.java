package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * 图片文件数据库对应类
 * Created by yuanjx on 2017/12/8.
 */
public class ImageFileEntity extends FileEntity implements Serializable{
    private static final long serialVersionUID = 4418098648262754605L;

    @Override
    public String toString() {
        return "ImageFileDao{" +
                "type='" + type + '\'' +
                ", fileParams='" + fileParams + '\'' +
                "} " + super.toString();
    }

    /**
     * 含义不明
     */
    private String type;

    /**
     * 文件上传时附带的参数
     */
    private String fileParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileParams() {
        return fileParams;
    }

    public void setFileParams(String fileParams) {
        this.fileParams = fileParams;
    }
}
