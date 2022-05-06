package com.example.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * SpringBoot Application Todos
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
public class BackendApplication {


    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
