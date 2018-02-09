package com.alfredo.assignment.stocksApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring configuration class.
 *
 * @author aramosk
 */
@Configuration
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:9090")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("Content-Type")
                .exposedHeaders("header-1", "header-2", "header-3")
                .allowCredentials(false)
                .maxAge(6000);

    }

}
