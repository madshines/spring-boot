package com.madshines.springbootjpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author:madshines
 * Date:2020/5/23
 * Package:com.madshines.springbootjpa.pojo
 * Description:教室实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class MyClass {
    @Id
    private Integer classId;
    @Column(name = "class_name")
    private String className;
}
