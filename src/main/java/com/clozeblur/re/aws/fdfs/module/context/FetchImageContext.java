package com.clozeblur.re.aws.fdfs.module.context;


import com.clozeblur.re.aws.util.TableIdxUtils;

/**
 * 图片文件基础信息, service类中与图片相关的方法尽量使用该类做参数.
 * 类中所有字段都非null
 * Created by yuanjx on 2017/12/8.
 */
public class FetchImageContext {
    private Long fileId;
    private String virtualPath;
    private String domain;
    private String matcher;

    public FetchImageContext(Long fileId, String virtualPath, String domain, String matcher) {
        this.fileId = fileId;
        this.virtualPath = virtualPath;
        this.domain = domain;
        this.matcher = matcher;
    }

    public Long getFileId() {
        return fileId;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public String getDomain() {
        return domain;
    }

    public String getMatcher() {
        return matcher;
    }

    public Integer getTableIdx() {
        return TableIdxUtils.tableIdx(virtualPath);
    }
}
