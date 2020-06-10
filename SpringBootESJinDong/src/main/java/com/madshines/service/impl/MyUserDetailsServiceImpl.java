package com.madshines.service.impl;

import com.madshines.pojo.Role;
import com.madshines.pojo.User;
import com.madshines.repository.RoleRepository;
import com.madshines.repository.UserRepository;
import com.madshines.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.service.impl
 * @version: 1.0
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        logger.info("用户的用户名："+name);
        User userName = userRepository.findByUserName(name);
        if (userName==null){
//            throw new
        }
        List<Role> roles = roleRepository.findRoleByUserName(name);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        org.springframework.security.core.userdetails.User user=new org.springframework.security.core.userdetails.User(userName.getName(),userName.getPassWord(),authorities);
        logger.info(roles.toString());
        return user;
    }
}
