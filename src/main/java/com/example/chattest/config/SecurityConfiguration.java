package com.example.chattest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // Login configuration
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/messages", true)
                .permitAll()
                // Logout configuration
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                // Pages only viewable when logged in
                .and()
        /////////////////////////////OLD STYLE///////////////////////////
                .authorizeRequests()
                .antMatchers(
                        "/messages"
                )
                .authenticated()
                // Pages viewable without logging in
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/"
                )
                .permitAll()

                ////////////////////////////////NEW WAY/////////////////////
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(
//                                "/messages"
//                        )
//                        .authenticated()
//                        // Pages viewable without logging in
//                        .requestMatchers(
//                                "/"
//                        )
//                        .permitAll()
//                )
        ;
        return http.build();
    }

}
