//package com.sharding.shardexample.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter
//{
//
//    @Bean
//    public OpenEntityManagerInViewFilter shard1OpenEntityManagerInViewFilter()
//    {
//        OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
//        osivFilter.setEntityManagerFactoryBeanName("shard1EntityManagerFactory");
//        return osivFilter;
//    }
//
//    @Bean
//    public OpenEntityManagerInViewFilter shard2OpenEntityManagerInViewFilter()
//    {
//        OpenEntityManagerInViewFilter osivFilter = new OpenEntityManagerInViewFilter();
//        osivFilter.setEntityManagerFactoryBeanName("shard2EntityManagerFactory");
//        return osivFilter;
//    }
//}
