package com.madshines.springbootmybatis.service;

import com.madshines.springbootmybatis.pojo.Student;

import java.util.List;

/**
 * Author:madshines
 * Date:2020/5/17
 * Package:com.madshines.springbootmybatis.service
 **/
public interface StudentService {
    List<Student> studentList();
    int deleteStudentById(int id);
}
