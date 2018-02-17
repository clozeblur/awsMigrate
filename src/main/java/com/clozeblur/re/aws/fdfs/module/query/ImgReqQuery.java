package com.clozeblur.re.aws.fdfs.module.query;

import org.springframework.util.StringUtils;

/**
 * http请求中抽取的图片查询参数.
 * 包括: virtualPath, prototype matcher, 等
 * Created by yuanjx on 2017/12/8.
 */
public class ImgReqQuery extends AbstractQuery{

    /**
     * 图片虚拟路径,不包括后缀
     */
    private String virtualPath;

    /**
     * 图片缩放matcher
     */
    private String matcher;

    /**
     * 图片文件后缀
     */
    private String ext;

    public ImgReqQuery(String virtualPath, String matcher, String ext) throws Exception {
        if (StringUtils.isEmpty(virtualPath)) {
            throw new Exception("virtualPath 为空.");
        }

        if (StringUtils.isEmpty(ext)) {
            throw new Exception("ext 为空.");
        }

        if (StringUtils.isEmpty(matcher)) {
            throw new Exception("matcher 为空. ");
        }

        this.virtualPath = virtualPath;
        this.matcher = matcher;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "ImgReqQuery{" +
                "virtualPath='" + virtualPath + '\'' +
                ", matcher='" + matcher + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
