package com.xxbb.springbootapi.config;

import com.xxbb.springbootapi.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用PreAuthorize注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//开启权限认证
                //放行路径必须写在前面
                .mvcMatchers("/user/login").permitAll()
                .mvcMatchers("/doc.html").permitAll()
                .mvcMatchers("/swagger-resources/**").permitAll()
                .mvcMatchers("/v2/api-docs/**").permitAll()
                .mvcMatchers("/webjars/**").permitAll()
                .mvcMatchers("/favicon.icon").permitAll()
                .mvcMatchers("/user/logout").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().successHandler(new AuthSuccHdl()) //登录成功处理
                .failureHandler(new AuthFailHdl()) //登录失败处理
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//不通过session获取securityContext
                .logout().logoutSuccessHandler(new LogoutSuccHdl()) //登出成功处理

                .and().csrf().disable() //禁用跨域
//                .and().csrf() //启用跨域
//                .and()
                .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)//添加jwt过滤器
                .exceptionHandling().authenticationEntryPoint(new AuthEntryPoint());//屏蔽默认登陆页面


    ;}

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 认证管理器
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}