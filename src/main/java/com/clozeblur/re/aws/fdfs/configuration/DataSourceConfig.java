package com.clozeblur.re.aws.fdfs.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by yuanjx
 * on 2017/12/4
 */
@Configuration
@MapperScan(basePackages = DataSourceConfig.packageName, sqlSessionFactoryRef = "commonSqlSessionFactory")
@EnableConfigurationProperties(CommonDSProperties.class)
public class DataSourceConfig {

    static final String packageName = "另一个mapper位置";

    private final CommonDSProperties commonDSProperties;

    private final ResourceLoader resourceLoader;

    public DataSourceConfig(CommonDSProperties commonDSProperties, ResourceLoader resourceLoader) {
        this.commonDSProperties = commonDSProperties;
        this.resourceLoader = resourceLoader;
    }

    @Bean("commonDataSource")
    @Primary
    public DataSource commonDataSource() {
        return Commons.generateDatasource(commonDSProperties);
    }

    @Bean(name = "commonTransactionManager")
    @Primary
    public DataSourceTransactionManager commonTransactionManager(@Qualifier("commonDataSource") DataSource commonDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(commonDataSource);
        return transactionManager;
    }

    @Bean(name = "commonSqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("commonDataSource") DataSource commonDataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(commonDataSource);
        Resource configLocation = resourceLoader.getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(configLocation);
        return sqlSessionFactory;
    }
}
