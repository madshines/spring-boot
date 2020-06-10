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
@Table(name = "user_role_rel")
public class UserRoleRel {
    @Id
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "role_id")
    private int roleId;
}
