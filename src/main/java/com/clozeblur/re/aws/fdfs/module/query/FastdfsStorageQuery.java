package com.clozeblur.re.aws.fdfs.module.query;

/**
 * fastdfs 查询数据类
 * Created by yuanjx on 2017/12/14.
 */
public class FastdfsStorageQuery {

    private String group;
    private String path;

    public FastdfsStorageQuery() {
    }

    public FastdfsStorageQuery(String group, String path) {
        this.group = group;
        this.path = path;
    }

    @Override
    public String toString() {
        return "FastdfsStorageQuery{" +
                "group='" + group + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
