package com.madshines.springbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Author:madshines
 * Date:2020/5/23
 * Package:com.madshines.springbootjpa.config
 * Description:主数据源配置
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.madshines.springbootjpa.repository.test1"}
,entityManagerFactoryRef = "primaryEntityManagerFactoryBean",transactionManagerRef = "primaryJpaTransactionManager")
public class PrimaryDataSourceConfig {
    /*
    * 注入主数据源和jpa配置*/
    @Autowired
    @Qualifier(value = "primaryDataSource")
    private DataSource primaryDataSource;
    @Autowired
    @Qualifier(value = "vendorProperties")
    private Map<String,Object> vendorProperties;
    /*
    * 创建本地容器实体类管理bean*/
    @Primary
    @Bean(value = "primaryEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(primaryDataSource)
                .properties(vendorProperties)
                .packages("com.madshines.springbootjpa.pojo")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }
    /*
    * 创建真正的entityManager*/
    @Primary
    @Bean(value = "primaryEntityManager")
    public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder){
        return primaryEntityManagerFactoryBean(builder).getObject().createEntityManager();
    }
    /*
    * 加入事务管理*/
    @Primary
    @Bean(value = "primaryJpaTransactionManager")
    public JpaTransactionManager primaryJpaTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(primaryEntityManagerFactoryBean(builder).getObject());
    }
}
