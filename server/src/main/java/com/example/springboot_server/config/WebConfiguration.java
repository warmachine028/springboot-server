package com.example.springboot_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @SuppressWarnings("deprecation")
    @Override
    public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }
}
