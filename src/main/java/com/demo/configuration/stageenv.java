package com.demo.configuration;

import org.springframework.beans.factory.annotation.Value;

public class stageenv implements Envconfig {

	@Value("${spring.datasource.url.stage}")
	private String mySqlDBUrl;

	@Value("${spring.datasource.user.stage}")
	private String mySqlDBUser;

	@Value("${spring.datasource.password.stage}")
	private String mySqlDBPassword;

	@Value("${spring.datasource.db.driver}")
	private String mySqlDBDriver;

	@Value("${scaffold.server.app.url.stage}")
	private String appUrl;

	@Override
	public String getEnvironment() {
		return "Dev Environment";
	}

	@Override
	public String getMySqlDBUrl() {
		return mySqlDBUrl;
	}

	@Override
	public String getMySqlDBUser() {
		return mySqlDBUser;
	}

	@Override
	public String getMySqlDBPassword() {
		return mySqlDBPassword;
	}

	@Override
	public String getMySqlDBDriver() {
		return mySqlDBDriver;
	}

	@Override
	public String getAppUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}

