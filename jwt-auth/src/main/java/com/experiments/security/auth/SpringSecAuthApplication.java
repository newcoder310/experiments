package com.experiments.security.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringSecAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecAuthApplication.class, args);
    }

}

