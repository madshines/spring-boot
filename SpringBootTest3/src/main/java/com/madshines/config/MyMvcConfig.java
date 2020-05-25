package com.madshines.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * Author:madshines
 * Date:2020/5/24
 * Package:com.madshines.config
 * Description:自定义mvc配置
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/madshines").setViewName("madshines");
    }

    /*
    * 自定义视图解析器，实现了ViewResolver接口的类就可看作是视图解析器*/
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }
    //自定义了一个视图解析器MyViewResolver
    public static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
