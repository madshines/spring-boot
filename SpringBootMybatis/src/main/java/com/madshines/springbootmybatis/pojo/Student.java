package com.madshines.springbootmybatis.pojo;

import lombok.Data;

/**
 * Author:madshines
 * Date:2020/5/17
 * Package:com.madshines.springbootmybatis.pojo
 * Description:学生实体类
 **/
@Data
public class Student {
    private int userId;
    private String userName;
    private String sex;
    private String birthYear;
}
