package com.clozeblur.re.aws.fdfs.module.entity;


/**
 * PrototypeEntity
 * Created by yuanjx on 2017/12/16.
 */
public class PrototypeEntity extends BaseEntity{


    private static final long serialVersionUID = -3960786826754634964L;

    private String name;
    private String matcher;
    private String remark;
    private String zoom;
    private String watermark;
    private Integer quality;
    private Integer status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
