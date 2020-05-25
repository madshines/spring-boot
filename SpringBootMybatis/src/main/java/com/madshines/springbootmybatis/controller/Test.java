package com.madshines.springbootmybatis.controller;

import com.madshines.springbootmybatis.pojo.Student;
import com.madshines.springbootmybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:madshines
 * Date:2020/5/17
 * Package:com.madshines.springbootmybatis.controller
 * Description:测试
 **/
@RestController
public class Test {
    @Autowired
    StudentService studentService;
    @RequestMapping("select")
    public String select(){
        List<Student> students = studentService.studentList();
        return students.toString();
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        int i = studentService.deleteStudentById(id);
        return String.valueOf(i);
    }
}
