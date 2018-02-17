package com.clozeblur.re.aws.fdfs.configuration;

/**
 * Created by yuanjx
 * on 2017/12/4
 */
public interface PropertiesModel {

    public String getDriver();

    public String getUrl();

    public String getUsername();

    public String getPassword();

    public Integer getMaxActive();

    public Integer getInitialSize();

    public Integer getMaxWait();

    public Integer getMaxIdle();

    public Integer getMinIdle();
}
