package com.clozeblur.re.aws.fdfs.module.entity;

/**
 * 缩放图片信息
 * BaseDao 中 id 对应数据库中file_id
 * Created by yuanjx on 2017/12/13.
 */
public class GenImageFileEntity extends BaseEntity {

    private static final long serialVersionUID = 4938954006261538384L;
    private Long generateKey;
    private Integer deleteState;
    private Integer version;
    private String zoom;
    private String watermark;
    private String signature;
    private Long size;

    @Override
    public String toString() {
        return "GenImageFileDao{" +
                "generateKey=" + generateKey +
                ", deleteState=" + deleteState +
                ", version=" + version +
                ", zoom='" + zoom + '\'' +
                ", watermark='" + watermark + '\'' +
                ", signature='" + signature + '\'' +
                ", size=" + size +
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
