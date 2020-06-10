package com.madshines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String passWord;
}
