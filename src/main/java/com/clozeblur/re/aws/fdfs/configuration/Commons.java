package com.clozeblur.re.aws.fdfs.configuration;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Created by yuanjx
 * on 2017/12/4
 */
public class Commons {

    public static BasicDataSource generateDatasource(PropertiesModel model) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(model.getDriver());
        dataSource.setUrl(model.getUrl());
        dataSource.setUsername(model.getUsername());
        dataSource.setPassword(model.getPassword());
        dataSource.setMaxActive(model.getMaxActive());
        dataSource.setInitialSize(model.getInitialSize());
        dataSource.setMaxIdle(model.getMaxIdle());
        dataSource.setMinIdle(model.getMinIdle());
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(180);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("select 1");
        dataSource.setValidationQueryTimeout(1);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setMinEvictableIdleTimeMillis(60000);
        dataSource.setNumTestsPerEvictionRun(20);
        dataSource.setDefaultAutoCommit(true);
        return dataSource;
    }
}
