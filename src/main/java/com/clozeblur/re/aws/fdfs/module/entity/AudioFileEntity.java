package com.clozeblur.re.aws.fdfs.module.entity;

import java.io.Serializable;

/**
 * 录音文件数据库对应类
 *
 * Created by yuanjx on 2017/12/8.
 */
public class AudioFileEntity extends FileEntity implements Serializable {
    private static final long serialVersionUID = -8263414720992238190L;

    @Override
    public String toString() {
        return "AudioFileEntity{} " + super.toString();
    }
}
