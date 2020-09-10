package com.core.webapi.config.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ServerSecurityConfiguration  extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public ServerSecurityConfiguration(
            UserDetailsService userDetailsService
    ){
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(userPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // To allow Pre-flight [OPTIONS] request from browser
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }
*/

    @Bean
    PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}
