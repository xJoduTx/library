package com.project.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.project.library")
public class SpringConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://5.159.102.84:5173") // Адрес React приложения
                .allowedHeaders("Authorization", "authorization")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
