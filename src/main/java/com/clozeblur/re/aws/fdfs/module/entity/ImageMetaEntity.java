package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * fs_image_metadata
 * Created by yuanjx on 2017/12/15.
 */
public class ImageMetaEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 8406807769015379433L;
    private Long generateKey;
    private Integer deleteState;
    private Integer version;
    private String format;
    private Integer width;
    private Integer height;
    private String signature;
    private Integer bitsPerPixel;
    private String colorType;


    @Override
    public String toString() {
        return "ImageMetaDao{" +
                "generateKey=" + generateKey +
                ", deleteState=" + deleteState +
                ", version=" + version +
                ", format='" + format + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", signature='" + signature + '\'' +
                ", bitsPerPixel=" + bitsPerPixel +
                ", colorType='" + colorType + '\'' +
                "} " + super.toString();
    }

    public Long getGenerateKey() {
        return generateKey;
    }

    public void setGenerateKey(Long generateKey) {
        this.generateKey = generateKey;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getBitsPerPixel() {
        return bitsPerPixel;
    }

    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }
}
