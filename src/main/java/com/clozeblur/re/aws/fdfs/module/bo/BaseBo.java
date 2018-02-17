package com.clozeblur.re.aws.fdfs.module.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Bo基础类
 * Created by yuanjx on 2017/12/8.
 */
public abstract class BaseBo implements Serializable{

    private static final long serialVersionUID = 3214717819955353020L;
    protected Long id;
    protected Date createTime;
    protected Date updateTime;

    @Override
    public String toString() {
        return "BaseBo{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
