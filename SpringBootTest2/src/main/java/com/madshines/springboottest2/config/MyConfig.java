package com.madshines.springboottest2.config;

import com.madshines.springboottest2.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:madshines
 * Date:2020/5/6
 * Package:com.madshines.springboottest2.config
 **/
@Configuration
public class MyConfig {
    @Bean
    public HelloService helloService(){
        System.out.println("配置类");
        return new HelloService();
    }
}
