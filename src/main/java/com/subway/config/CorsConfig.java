package com.subway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000","https://sub-recipe.com","http://localhost")
                .allowedMethods("GET","POST","PUT","DELETE","OPTION")
                .allowCredentials(true)
                .maxAge(3000)
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
    }
}
