package com.madshines.repository;

import com.madshines.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.repository
 * @version: 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery=true,value = "select * from user where name=:name")
    public User findByUserName(@Param("name") String name);
}
