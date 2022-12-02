package com.example.product_catalog.security;

import com.example.product_catalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("abdelrahman")
//                .password("test1")
//                .roles(ApplicationUserRole.CUSTOMER.name())
//                .and()
//                .withUser("abdelrahman2")
//                .password("test1")
//                .roles("ADMIN");
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .antMatchers("/","index","/css/*","/js/*").permitAll()
//                .antMatchers("/product").hasRole(ApplicationUserRole.CUSTOMER.name())
//                .anyRequest()
//                .authenticated()
////                .antMatchers("/","/product","/product/**").permitAll()
////                .antMatchers("/order/**").hasAnyRole("ADMIN","USER")
//                .and()
//                .httpBasic();
//    }
//

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/sign-up","/category","/product").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
