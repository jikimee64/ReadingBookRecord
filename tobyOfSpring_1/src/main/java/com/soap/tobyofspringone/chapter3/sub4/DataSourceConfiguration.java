package com.soap.tobyofspringone.chapter3.sub4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfiguration {

    @Value("$")
    private String dbDriverClassName;

    @Value("$")
    private String dbURL;

    @Value("$")
    private String userName;

    @Value("$")
    private String password;

    @Bean
    public DataSource dataSource() {
        log.info("Connecting to database...");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(dbURL);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }
}