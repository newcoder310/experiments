package com.profiles.profilestest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.profiles.profilestest")
public class ProfilesTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilesTestApplication.class, args);
	}

}
