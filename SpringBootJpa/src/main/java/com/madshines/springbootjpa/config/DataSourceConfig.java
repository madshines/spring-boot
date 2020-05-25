package com.madshines.springbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Author:madshines
 * Date:2020/5/23
 * Package:com.madshines.springbootjpa.config
 * Description:数据源配置
 **/
@Configuration
public class DataSourceConfig {
    /*
    * 配置主数据源*/
    @Primary
    @Bean(value = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
    * 配置第二数据源*/
    @Bean(value = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
    * 配置Jpa*/
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;
    @Bean(value = "vendorProperties")
    public Map<String,Object> vendorProperties(){
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(),new HibernateSettings());
    }
}
