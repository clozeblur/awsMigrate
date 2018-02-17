package com.clozeblur.re;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.clozeblur.core", "com.clozeblur.sh"})
@EnableScheduling
@EnableAsync
public class AwsMigrateApplication implements TransactionManagementConfigurer {

	@Autowired
	@Qualifier(value = "commonTransactionManager")
	private DataSourceTransactionManager commonTransactionManager;

	@Autowired
	@Qualifier(value = "exTransactionManager")
	private DataSourceTransactionManager exTransactionManager;


	public static void main(String[] args) {
		SpringApplication.run(AwsMigrateApplication.class, args);
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return commonTransactionManager;
	}
}
