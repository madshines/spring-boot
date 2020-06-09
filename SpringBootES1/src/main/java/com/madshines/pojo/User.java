package com.madshines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:madshines
 * Date:2020/6/7
 * Package:com.madshines.pojo
 * Description:User实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String sex;
}
