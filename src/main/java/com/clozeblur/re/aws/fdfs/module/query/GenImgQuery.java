package com.clozeblur.re.aws.fdfs.module.query;

/**
 * 查找缩放图片参数
 * Created by yuanjx on 2017/12/10.
 */
public class GenImgQuery extends AbstractQuery{

    private Long fileId;
    private String virtualPath;
    private String domain;
    private String zoom;
    private String watermark;
    private String signature;
    private Integer quality;

    @Override
    public String toString() {
        return "GenImgQuery{" +
                "fileId=" + fileId +
                ", virtualPath='" + virtualPath + '\'' +
                ", domain='" + domain + '\'' +
                ", zoom='" + zoom + '\'' +
                ", watermark='" + watermark + '\'' +
                ", signature='" + signature + '\'' +
                ", quality=" + quality +
                "} " + super.toString();
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
