package com.clozeblur.re.aws.migrate.configuration;

import com.clozeblur.re.aws.fdfs.configuration.Commons;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by yuanjx
 * on 2017/12/1
 */
@Configuration
@MapperScan(basePackages = ExDataSourceConfig.packageName, sqlSessionFactoryRef = "exSqlSessionFactory")
@EnableConfigurationProperties(ExDSProperties.class)
public class ExDataSourceConfig {

    static final String packageName = "mapper位置";

    private final ExDSProperties exDSProperties;

    private final ResourceLoader resourceLoader;

    public ExDataSourceConfig(ExDSProperties exDSProperties, ResourceLoader resourceLoader) {
        this.exDSProperties = exDSProperties;
        this.resourceLoader = resourceLoader;
    }

    @Bean(name = "exDataSource")
    public DataSource exDataSource() {
        return Commons.generateDatasource(exDSProperties);
    }

    @Bean(name = "exTransactionManager")
    @ConditionalOnBean(name = "exDataSource")
    public DataSourceTransactionManager exTransactionManager() {
        DataSourceTransactionManager exTransactionManager = new DataSourceTransactionManager();
        exTransactionManager.setDataSource(exDataSource());
        return exTransactionManager;
    }

    @Bean(name = "exSqlSessionFactory")
    @ConditionalOnBean(name = "exDataSource")
    public SqlSessionFactoryBean exSqlSessionFactory() {
        SqlSessionFactoryBean exSqlSessionFactory = new SqlSessionFactoryBean();
        exSqlSessionFactory.setDataSource(exDataSource());
        Resource[] mappers = {resourceLoader.getResource("classpath:mybatis/mapper/AwsMigrateMapper.xml")};
        exSqlSessionFactory.setMapperLocations(mappers);
        return exSqlSessionFactory;
    }

    @Bean(name = "exSqlSession")
    @ConditionalOnBean(name = "exSqlSessionFactory")
    public SqlSessionTemplate exSqlSession() {
        try {
            return new SqlSessionTemplate(exSqlSessionFactory().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
