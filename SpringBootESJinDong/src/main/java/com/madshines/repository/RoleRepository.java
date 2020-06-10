package com.madshines.repository;

import com.madshines.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.repository
 * @version: 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(nativeQuery=true,value = "select * from role where id in (select a.role_id from user_role_rel as a where a.user_id in (select u.id from user as u where u.name=:name));")
    public List<Role> findRoleByUserName(@Param("name") String name);
}
