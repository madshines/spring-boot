package com.madshines.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author:madshines
 * Date:2020/5/5
 * Package:com.madshines.controller
 **/

@SpringBootApplication  //来标注一个主程序类，说明这个是一个Spring Boot应用
@ComponentScan(basePackages = {"com.madshines.controller"})
public class HelloWorldMain {
    public static void main(String[] args) {
        //Spring应用启动起来
        SpringApplication.run(HelloWorldMain.class,args);
        System.out.println("----");
    }
}
