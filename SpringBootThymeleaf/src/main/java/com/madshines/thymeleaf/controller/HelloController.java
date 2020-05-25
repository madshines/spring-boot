package com.madshines.thymeleaf.controller;

import com.madshines.thymeleaf.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * Author:madshines
 * Date:2020/5/8
 * Package:com.madshines.thymeleaf.controller
 **/
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }
    @RequestMapping("success")
    public ModelAndView success(){
        ModelAndView modelAndView=new ModelAndView();
        Student student=new Student();
        student.setId(1);
        student.setName("tom");
        modelAndView.addObject("student",student);
        modelAndView.addObject("id",student.getId());
        modelAndView.addObject("class",student.getName());
        modelAndView.addObject("list", Arrays.asList("list1","list2","list3","lis4"));
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
