package com.madshines.springbootjpa.repository.test2;

import com.madshines.springbootjpa.pojo.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author:madshines
 * Date:2020/5/23
 * Package:com.madshines.springbootjpa.repository.test1
 **/
public interface Test2Repository extends JpaRepository<MyClass,Long> {
}
