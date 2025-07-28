package com.desafioey.apiusuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Parámetros: saltLength, hashLength, parallelism, memory, iterations
        return new Argon2PasswordEncoder(16, 32, 1, 65536, 3);
    }
}
