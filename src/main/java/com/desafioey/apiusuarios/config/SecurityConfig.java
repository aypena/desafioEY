package com.desafioey.apiusuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para desarrollo
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite uso de frames (requerido por H2)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Permite acceso sin autenticación a H2
                        .anyRequest().permitAll() // Permite el resto también
                );
        return http.build();
    }
}
