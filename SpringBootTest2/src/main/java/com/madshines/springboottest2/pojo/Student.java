package com.madshines.springboottest2.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Author:madshines
 * Date:2020/5/6
 * Package:com.madshines.springboottest2.pojo
 **/
//@PropertySource(value = {"classpath:student.properties"})//记载指定配置文件
@Data
@Component
@ConfigurationProperties(prefix = "student")
public class Student {
    private Integer id;
    private String name;
    private Dog dog;
    private List<String> list;
    private Map<Object,String> map;
}
