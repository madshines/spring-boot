package com.madshines.repository;

import com.madshines.pojo.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:madshines
 * Date:2020/6/9
 * Package:com.madshines.repository
 * Description:Content
 **/
@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {

}
