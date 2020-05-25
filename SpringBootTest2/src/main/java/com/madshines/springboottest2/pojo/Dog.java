package com.madshines.springboottest2.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Author:madshines
 * Date:2020/5/6
 * Package:com.madshines.springboottest2.pojo
 **/
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "dog")
@Validated //数据校验
public class Dog {
    private Integer dogId;
    private String dogName;
}
