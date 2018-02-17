package com.clozeblur.re.aws.fdfs.module.query;


public class AudioFileQuery extends AbstractQuery {
    private String virtualPath;

    public AudioFileQuery(){}

    public AudioFileQuery(String virtualPath) {
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
