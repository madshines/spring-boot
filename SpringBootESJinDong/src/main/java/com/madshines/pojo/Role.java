package com.madshines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.pojo
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
}
