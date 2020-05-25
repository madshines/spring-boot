package com.madshines.springbootmybatis.mapper;

import com.madshines.springbootmybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author:madshines
 * Date:2020/5/17
 * Package:com.madshines.springbootmybatis.mapper
 * Description:学生映射
 **/
//这是一个操作数据库的mapper
//使用@mapper或者@MapperScan都可以，如果映射文件多的话，则使用@MapperScan
@Mapper
public interface StudentMapper {
    //@Select("select * from student")
    List<Student> studentList();
    //@Delete("delete from student where user_id=#{id}")
    int deleteStudentById(int id);
}
