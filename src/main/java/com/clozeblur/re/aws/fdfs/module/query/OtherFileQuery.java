package com.clozeblur.re.aws.fdfs.module.query;

/**
 * OtherFileQuery
 * Created by yuanjx on 2017/12/14.
 */
public class OtherFileQuery extends AbstractQuery {

    private String virtualPath;

    public OtherFileQuery(){}

    public OtherFileQuery(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    @Override
    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }
}
