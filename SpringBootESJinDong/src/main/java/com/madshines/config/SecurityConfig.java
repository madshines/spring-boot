package com.madshines.config;

import com.madshines.provider.MyAuthenticationProvider;
import com.madshines.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Author :madshines
 * @Date: 2020-06-10
 * @Description: com.madshines.config
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    //配置自定义校验规则，密码编码，使用我们自定义的校验器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("刘鑫").password(new BCryptPasswordEncoder().encode("123456")).roles("role1")
//                .and()
//                .withUser("张宇恒").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signIn","/Mylogin.html").permitAll()
                .antMatchers("/index").hasRole("admin")
                .antMatchers("/index").hasRole("customer");
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
//        http.authorizeRequests()
//                // 放行登录
//                .antMatchers("/login/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // 开启表单认证
//                .formLogin()
//                // 地址写的是 映射的路径
//                .loginPage("/login.html")
//                // 必须添加
//                .loginProcessingUrl("/login")
//                .permitAll()
//                // 第二个参数，如果不写成true，则默认登录成功以后，访问之前被拦截的页面，而非去我们规定的页面
//                .defaultSuccessUrl("/index.html", true)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic();
//
//    }
}
