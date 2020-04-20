package com.profiles.profilestest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("dev")
@Configuration
public class DevConfig {

    @PostConstruct
    public void init(){
        System.out.println("Loaded dev");
    }
}
