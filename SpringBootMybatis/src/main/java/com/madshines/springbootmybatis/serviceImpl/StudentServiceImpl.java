package com.madshines.springbootmybatis.serviceImpl;

import com.madshines.springbootmybatis.mapper.StudentMapper;
import com.madshines.springbootmybatis.pojo.Student;
import com.madshines.springbootmybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:madshines
 * Date:2020/5/17
 * Package:com.madshines.springbootmybatis.serviceImpl
 * Description:
 **/
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Student> studentList() {
        return studentMapper.studentList();
    }

    @Override
    public int deleteStudentById(int id) {
        return studentMapper.deleteStudentById(id);
    }
}
