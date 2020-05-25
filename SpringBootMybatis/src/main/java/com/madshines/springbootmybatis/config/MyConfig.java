package com.madshines.springbootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Author:madshines
 * Date:2020/5/15
 * Package:com.madshines.springbootjdbc.config
 * Description:这是druid的配置
 **/
@Configuration
public class MyConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    //配置druid监控
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //HashMap<String, String> map1 = new HashMap<>();
//        map1.put("loginUserName","root");
//        map1.put("loginPassword","123456");
//        map1.put("allow","198.168.2.154");
//        map1.put("deny","127.0.0.1");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
//        map1.put("resetEnable", "false");
        servletRegistrationBean.addInitParameter("loginUsername","root");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
//        servletRegistrationBean.addInitParameter("allow","192.168.18.4");
//        servletRegistrationBean.addInitParameter("deny","192.168.18.4");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return  servletRegistrationBean;
    }
    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
//        HashMap<String, String> map2 = new HashMap<>();
//        map2.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.setInitParameters(map2);
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return  filterRegistrationBean;
    }

}
