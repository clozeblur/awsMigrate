package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * 其它文件数据库对应类
 * Created by yuanjx on 2017/12/8.
 */
public class OtherFileEntity extends FileEntity implements Serializable {

    private static final long serialVersionUID = 4068673380657785039L;

    @Override
    public String toString() {
        return "OtherFileEntity{} " + super.toString();
    }
}
