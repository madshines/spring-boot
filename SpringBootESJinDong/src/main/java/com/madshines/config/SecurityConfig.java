package com.madshines.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.config
 * @version: 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("刘鑫").password(new BCryptPasswordEncoder().encode("123456")).roles("role1")
                .and()
                .withUser("张宇恒").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signIn","/Mylogin.html").permitAll()
                .antMatchers("/index").hasRole("role1")
                .antMatchers("/index").hasRole("admin");
        http.logout().logoutSuccessUrl("/index");
        http.csrf().disable();
        http.rememberMe().rememberMeParameter("remember");
        http.formLogin().loginPage("/Mylogin.html")
                .loginProcessingUrl("/signIn").successForwardUrl("/index")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
