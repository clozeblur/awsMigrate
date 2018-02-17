package com.clozeblur.re.aws.fdfs.module.entity;


import java.util.Date;

/**
 * 图片分辨率信息
 * Created by yuanjx on 2017/12/13.
 */
public class DomainPrototypeEntity extends BaseEntity {

    private static final long serialVersionUID = 8941452784941262295L;
    //fs_service_domain_prototype 对应字段
    private Long domainId;
    private Long prototypeId;
    private Integer needSave;
    private Integer status;

    //fs_service_domain 对应字段
    private String domainName;
    private String domainRemark;
    private Integer domainStatus;
    private Date domainCreateTime;
    private Date domainUpdateTime;

    //fs_service_prototypes 对应字段
    private String prototypeName;
    private String prototypeMatcher;
    private String prototypeRemark;
    private String prototypeZoom;
    private String prototypeWatermark;
    private Integer prototypeQuality;
    private Integer prototypeStatus;
    private Date prototypeCreateTime;
    private Date prototypeUpdateTime;

    @Override
    public String toString() {
        return "DomainPrototypeBo{" +
                "domainId=" + domainId +
                ", prototypeId=" + prototypeId +
                ", needSave=" + needSave +
                ", status=" + status +
                ", domainName='" + domainName + '\'' +
                ", domainRemark='" + domainRemark + '\'' +
                ", domainStatus=" + domainStatus +
                ", domainCreateTime=" + domainCreateTime +
                ", domainUpdateTime=" + domainUpdateTime +
                ", prototypeName='" + prototypeName + '\'' +
                ", prototypeMatcher='" + prototypeMatcher + '\'' +
                ", prototypeRemark='" + prototypeRemark + '\'' +
                ", prototypeZoom='" + prototypeZoom + '\'' +
                ", prototypeWatermark='" + prototypeWatermark + '\'' +
                ", prototypeQuality=" + prototypeQuality +
                ", prototypeStatus=" + prototypeStatus +
                ", prototypeCreateTime=" + prototypeCreateTime +
                ", prototypeUpdateTime=" + prototypeUpdateTime +
                "} " + super.toString();
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getPrototypeId() {
        return prototypeId;
    }

    public void setPrototypeId(Long prototypeId) {
        this.prototypeId = prototypeId;
    }

    public Integer getNeedSave() {
        return needSave;
    }

    public void setNeedSave(Integer needSave) {
        this.needSave = needSave;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainRemark() {
        return domainRemark;
    }

    public void setDomainRemark(String domainRemark) {
        this.domainRemark = domainRemark;
    }

    public Integer getDomainStatus() {
        return domainStatus;
    }

    public void setDomainStatus(Integer domainStatus) {
        this.domainStatus = domainStatus;
    }

    public Date getDomainCreateTime() {
        return domainCreateTime;
    }

    public void setDomainCreateTime(Date domainCreateTime) {
        this.domainCreateTime = domainCreateTime;
    }

    public Date getDomainUpdateTime() {
        return domainUpdateTime;
    }

    public void setDomainUpdateTime(Date domainUpdateTime) {
        this.domainUpdateTime = domainUpdateTime;
    }

    public String getPrototypeName() {
        return prototypeName;
    }

    public void setPrototypeName(String prototypeName) {
        this.prototypeName = prototypeName;
    }

    public String getPrototypeMatcher() {
        return prototypeMatcher;
    }

    public void setPrototypeMatcher(String prototypeMatcher) {
        this.prototypeMatcher = prototypeMatcher;
    }

    public String getPrototypeRemark() {
        return prototypeRemark;
    }

    public void setPrototypeRemark(String prototypeRemark) {
        this.prototypeRemark = prototypeRemark;
    }

    public String getPrototypeZoom() {
        return prototypeZoom;
    }

    public void setPrototypeZoom(String prototypeZoom) {
        this.prototypeZoom = prototypeZoom;
    }

    public String getPrototypeWatermark() {
        return prototypeWatermark;
    }

    public void setPrototypeWatermark(String prototypeWatermark) {
        this.prototypeWatermark = prototypeWatermark;
    }

    public Integer getPrototypeQuality() {
        return prototypeQuality;
    }

    public void setPrototypeQuality(Integer prototypeQuality) {
        this.prototypeQuality = prototypeQuality;
    }

    public Integer getPrototypeStatus() {
        return prototypeStatus;
    }

    public void setPrototypeStatus(Integer prototypeStatus) {
        this.prototypeStatus = prototypeStatus;
    }

    public Date getPrototypeCreateTime() {
        return prototypeCreateTime;
    }

    public void setPrototypeCreateTime(Date prototypeCreateTime) {
        this.prototypeCreateTime = prototypeCreateTime;
    }

    public Date getPrototypeUpdateTime() {
        return prototypeUpdateTime;
    }

    public void setPrototypeUpdateTime(Date prototypeUpdateTime) {
        this.prototypeUpdateTime = prototypeUpdateTime;
    }
}
