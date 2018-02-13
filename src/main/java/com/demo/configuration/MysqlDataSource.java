package com.demo.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MysqlDataSource {
	@Autowired
	private Envconfig envConfig;

	@Bean
	public DataSource dataSource() {

		PoolProperties poolProperties = new PoolProperties();
		poolProperties.setUrl(envConfig.getMySqlDBUrl());
		System.out.println("envConfig.getMySqlDBDriver()---"+envConfig.getMySqlDBDriver());
		poolProperties.setDriverClassName(envConfig.getMySqlDBDriver());
		poolProperties.setUsername(envConfig.getMySqlDBUser());
		poolProperties.setPassword(envConfig.getMySqlDBPassword().trim());
		poolProperties.setJmxEnabled(true);
		poolProperties.setTestWhileIdle(true);
		poolProperties.setTestOnBorrow(true);

		DataSource datasource = new DataSource();
		datasource.setPoolProperties(poolProperties);

		return datasource;
	}
}