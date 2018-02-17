package com.clozeblur.re.aws.fdfs.module.entity;

/**
 * DomainEntity
 * Created by yuanjx on 2017/12/16.
 */
public class DomainEntity extends BaseEntity {
    private static final long serialVersionUID = 6085388382495245167L;

    private String name;
    private String remark;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
