package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * DAO 基础类
 * 包含 id, createTime, updateTime
 * Created by yuanjx on 2017/12/7.
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5207767640287652923L;
    protected Long id;
    protected Date createTime;
    protected Date updateTime;
    protected Integer tableIdx;

    @Override
    public String toString() {
        return "BaseDao{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTableIdx() {
        return tableIdx;
    }

    public void setTableIdx(Integer tableIdx) {
        this.tableIdx = tableIdx;
    }
}
