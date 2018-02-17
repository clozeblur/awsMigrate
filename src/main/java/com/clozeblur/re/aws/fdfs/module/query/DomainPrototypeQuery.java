package com.clozeblur.re.aws.fdfs.module.query;

/**
 * 缩放参数查询
 * Created by yuanjx on 2017/12/13.
 */
public class DomainPrototypeQuery {

    private String domain;
    private String matcher;

    public DomainPrototypeQuery() {
    }

    public DomainPrototypeQuery(String domain, String matcher) {
        this.domain = domain;
        this.matcher = matcher;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    @Override
    public String toString() {
        return "DomainPrototypeQuery{" +
                "domain='" + domain + '\'' +
                ", matcher='" + matcher + '\'' +
                '}';
    }
}
