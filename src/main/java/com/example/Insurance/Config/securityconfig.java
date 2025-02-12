package com.example.Insurance.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityconfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (optional)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all requests
                )
                .formLogin(form -> form.disable()) // Disable login form
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable basic auth (optional)

        return http.build();
    }
}
