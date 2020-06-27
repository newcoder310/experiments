package com.experiments.microservices.dbservice.presentation;

import com.experiments.microservices.dbservice.model.Quote;
import com.experiments.microservices.dbservice.model.Quotes;
import com.experiments.microservices.dbservice.service.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/db")
public class DBResource {

    @Autowired
    private QuotesService service;

    @GetMapping("{username}")
    public List<String> getQuotes(@PathVariable("username") String username) {
        return service.getQuotesByUsername(username);
    }

    @PostMapping("/add")
    public void add(@RequestBody final Quotes quotes) {
        service.addQuotes(quotes);
    }
}
