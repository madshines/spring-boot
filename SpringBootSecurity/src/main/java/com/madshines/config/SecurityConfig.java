package com.madshines.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author :madshines
 * @Date: 2020-05-25
 * @Description: com.madshines.config
 * @version: 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，里面的功能页只有有权限的人才可以访问
        http.authorizeRequests()
                .antMatchers("/","/index","/login","/toLogin").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //没有权限默认到登录页面，并且设置自定义登录页面
        http.formLogin().loginPage("/login.html")
                //设置登录form的action请求并设置登录成功后跳转的请求
        .loginProcessingUrl("/login").successForwardUrl("/index")
                //自定义登录form传来的用户名和密码参数，默认是username和password
                .usernameParameter("username").passwordParameter("password")
        .and()
        .authorizeRequests()
        .anyRequest().authenticated();
        //注销        注销成功后跳转的请求
        http.logout().logoutSuccessUrl("/index");
        //防止网站攻击 csrf，阻止get
        http.csrf().disable();
        //记住我,保存cookies  自定义接收前端参数
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    //在spring security 5.0新增了很多加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //   存放在内存中的认证信息     密码转码
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zyh").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
