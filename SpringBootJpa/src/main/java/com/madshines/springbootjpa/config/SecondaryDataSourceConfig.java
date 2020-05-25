package com.madshines.springbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * Description:次数据源配置
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.madshines.springbootjpa.repository.test2"}
,transactionManagerRef = "secondaryJpaTransactionManager",entityManagerFactoryRef = "secondaryEntityManagerFactoryBean")
public class SecondaryDataSourceConfig {
    @Autowired
    @Qualifier(value = "secondaryDataSource")
    private DataSource secondaryDataSource;
    @Autowired
    @Qualifier(value = "vendorProperties")
    private Map<String,Object> vendorProperties;

    @Bean(value = "secondaryEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(secondaryDataSource)
                .properties(vendorProperties)
                .persistenceUnit("secondaryPersistenceUnit")
                .packages("com.madshines.springbootjpa.pojo")
                .build();
    }
    @Bean(value = "secondaryEntityManager")
    public EntityManager secondaryEntityManager(EntityManagerFactoryBuilder builder){
        return secondaryEntityManagerFactoryBean(builder).getObject().createEntityManager();
    }
    @Bean(value = "secondaryJpaTransactionManager")
    public JpaTransactionManager secondaryJpaTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(secondaryEntityManagerFactoryBean(builder).getObject());
    }
}
