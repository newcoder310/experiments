package com.sharding.shardexample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.sharding.shardexample.model.shard", entityManagerFactoryRef = "shard1EntityManagerFactory",
        transactionManagerRef = "shard1TransactionManager" )
@EntityScan(basePackages = "com.sharding.shardexample.model.shard")
public class ShardConfiguration {
    @Autowired
    private Environment env;


    @Bean
    @ConfigurationProperties(prefix="datasource.shard1")
    public DataSourceProperties shard1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource shard1DataSource() {
        DataSourceProperties shard1DataSourceProperties = shard1DataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(shard1DataSourceProperties.getDriverClassName())
                .url(shard1DataSourceProperties.getUrl())
                .username(shard1DataSourceProperties.getUsername())
                .password(shard1DataSourceProperties.getPassword())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.shard2")
    public DataSourceProperties shard2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource shard2DataSource() {
        DataSourceProperties shard2DataSourceProperties = shard2DataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(shard2DataSourceProperties.getDriverClassName())
                .url(shard2DataSourceProperties.getUrl())
                .username(shard2DataSourceProperties.getUsername())
                .password(shard2DataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.SHARD1, shard1DataSource());
        targetDataSources.put(DBTypeEnum.SHARD2, shard2DataSource());
        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(shard1DataSource());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }

    @Bean
    public PlatformTransactionManager shard1TransactionManager()
    {
        EntityManagerFactory factory = shard1EntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean shard1EntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(multiRoutingDataSource());
        factory.setPackagesToScan(new String[]{"com.sharding.shardexample.model.shard"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean
    public DataSourceInitializer shard1DataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(shard1DataSource());
        //esourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        //databasePopulator.addScript(new ClassPathResource("shard1-data.sql"));
        //dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(env.getProperty("datasource.shard1.initialize", Boolean.class, true));
        dataSourceInitializer.setEnabled(env.getProperty("datasource.shard2.initialize", Boolean.class, true));
        return dataSourceInitializer;
    }
}
