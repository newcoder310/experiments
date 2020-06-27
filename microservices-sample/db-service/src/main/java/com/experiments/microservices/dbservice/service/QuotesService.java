package com.experiments.microservices.dbservice.service;

import com.experiments.microservices.dbservice.model.Quote;
import com.experiments.microservices.dbservice.model.Quotes;
import com.experiments.microservices.dbservice.model.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuotesService {

    @Autowired
    private QuotesRepository quotesRepository;

    public List<String> getQuotesByUsername(String username) {
        return quotesRepository.findByUsername(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    public void addQuotes(Quotes quotes) {
        quotes.getQuotes().stream()
                .map(quote -> new Quote(quotes.getUserName(),quote))
                .forEach(quote -> quotesRepository.save(quote));
    }
}
