package com.madshines.springbootjpa.repository.test1;

import com.madshines.springbootjpa.pojo.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author:madshines
 * Date:2020/5/23
 * Package:com.madshines.springbootjpa.repository.test1
 **/
public interface Test1Repository extends JpaRepository<MyClass,Long> {
    @Query(value="select * from class",nativeQuery=true)
    List<MyClass> getList();
    @Query(value="select * from class where classId=:id",nativeQuery=true)
    MyClass getById(@Param("id") Integer id);
//    @Query(value="insert into class values (?1,?2)")
//    Integer insertClass(Integer id,String name);

}
