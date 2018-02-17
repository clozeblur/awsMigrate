package com.clozeblur.re.aws.migrate.configuration;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
public enum StatusType {

    未处理(0),
    下载成功(1),
    下载失败(2),
    上传成功(3),
    上传失败(4),
    上传重试失败(5),
    异常(6);

    private Integer value;

    StatusType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
