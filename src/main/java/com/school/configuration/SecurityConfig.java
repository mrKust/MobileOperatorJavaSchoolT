package com.school.configuration;

import com.school.advice.securityAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * SecurityConfig class is the extension of spring's security
 * WebSecurityConfigAdapter. There we configure our request params
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Service which represents the implementation of spring's UserDetailsService.
     * It provides ud API for loading userDetails by email. After that security module
     * decides should it authorise user with those credentials or not
     */
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Method creating PasswordEncoder bean
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Method creating AccessDeniedHandler method
     * @return Advice which creates access denied exception handler
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new securityAdvice();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * This method describes parameters to access different pages and sets exceptions handler
     * @param http provide us API for configure http requests
     * @throws Exception in cases when something go wrong
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/common/**").hasAnyRole("client", "control")
                .antMatchers("/client/**").hasRole("client")
                .antMatchers("/control/**").hasRole("control")
                .antMatchers("/anonymous/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/errorPage").permitAll()
                .antMatchers("/").permitAll()
                .and().formLogin()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

}
