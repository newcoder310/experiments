package com.sharding.shardexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan(basePackages = "com.sharding.shardexample")
public class ShardExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardExampleApplication.class, args);
	}

}
