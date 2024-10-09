package com.yuu.springboot.demosecurity.security;

import com.yuu.springboot.demosecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    // add support for JDBC ... no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
        )
                .formLogin(form ->
                        form
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
        )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
