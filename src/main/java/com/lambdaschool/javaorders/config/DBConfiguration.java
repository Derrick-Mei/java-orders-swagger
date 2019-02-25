package com.lambdaschool.javaorders.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration
{
    @Bean
    public DataSource dataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        // name of database = snackbar.db -> can have a path to file instead, but it's easier to have the db where you need it
        dataSourceBuilder.url("jdbc:sqlite:orders.db");
        return dataSourceBuilder.build();
    }

}