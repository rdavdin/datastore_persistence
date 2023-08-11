package com.jdw.c3.entitydesign;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "rdav.jdw")
    public DataSource getDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/plant2nd?serverTimezone=UTC");
        return dataSourceBuilder.build();
    }
}
