package com.clozeblur.re.aws.fdfs.module.bo;

import java.io.Serializable;

/**
 * 域名,分辨率规则对应业务类
 * 由 fs_service_domain, fs_service_prototypes, fs_service_domain_prototype 三张表关联取出数据
 * Created by yuanjx on 2017/12/8.
 */
public class DomainPrototypeBo extends BaseBo implements Serializable{

    private static final long serialVersionUID = -6470761654366060883L;
    //fs_service_domain_prototype 对应字段

    private Integer needSave;
    private Integer status;

    //fs_service_domain 对应字段
    private String domainName;
    private String domainRemark;

    //fs_service_prototypes 对应字段
    private String prototypeName;
    private String prototypeMatcher;
    private String prototypeRemark;
    private String prototypeZoom;
    private String prototypeWatermark;
    private Integer prototypeQuality;

    @Override
    public String toString() {
        return "DomainPrototypeBo{" +
                "needSave=" + needSave +
                ", status=" + status +
                ", domainName='" + domainName + '\'' +
                ", domainRemark='" + domainRemark + '\'' +
                ", prototypeName='" + prototypeName + '\'' +
                ", prototypeMatcher='" + prototypeMatcher + '\'' +
                ", prototypeRemark='" + prototypeRemark + '\'' +
                ", prototypeZoom='" + prototypeZoom + '\'' +
                ", prototypeWatermark='" + prototypeWatermark + '\'' +
                ", prototypeQuality=" + prototypeQuality +
                "} " + super.toString();
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

}
