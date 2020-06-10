package com.madshines.provider;

import com.madshines.repository.UserRepository;
import com.madshines.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.provider
 * @version: 1.0
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        logger.info(name);
        String password = authentication.getCredentials().toString();
        logger.info(password);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(name);
//        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        boolean matches = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        if (password==userDetails.getPassword()){
            return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        }
        return null;
    }

    //确保该方法返回为true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
