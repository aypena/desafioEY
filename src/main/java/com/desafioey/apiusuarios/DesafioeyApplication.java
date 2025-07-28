package com.desafioey.apiusuarios;

import com.desafioey.apiusuarios.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class DesafioeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioeyApplication.class, args);
	}

}
