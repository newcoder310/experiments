package com.etcd.etcdtest;

import com.etcd.etcdtest.configuration.EtcdTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@ComponentScan(basePackages = "com.etcd.etcdtest.configuration")
@EnableScheduling
public class EtcdTestApplication {

	@Autowired
	private static EtcdTestConfiguration configuration;

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(EtcdTestApplication.class, args);
		configuration.test(args[0]);
	}

}
