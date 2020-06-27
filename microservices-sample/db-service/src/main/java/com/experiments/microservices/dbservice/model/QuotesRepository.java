package com.experiments.microservices.dbservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

        List<Quote> findByUsername(String username);

}
