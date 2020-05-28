package com.madshines.springbootes.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author:madshines
 * Date:2020/5/28
 * Package:com.madshines.springbootes.pojo
 * Description:User
 **/
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
}
