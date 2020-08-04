package com.springboot.georlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GeorlockApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GeorlockApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(new Class[]{GeorlockApplication.class});
    }
}
