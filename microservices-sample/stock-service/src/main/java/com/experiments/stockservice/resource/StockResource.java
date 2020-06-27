package com.experiments.stockservice.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("{username}")
    public List<Quote> getStock(@PathVariable("username") String userName) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> req = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/{username}", HttpMethod.GET,
                req, new ParameterizedTypeReference<List<String>>() {
                },userName);


        List<String> quotes = quoteResponse.getBody();
       return quotes.stream()
                .map(quote -> {
                    Stock stock = getStockPrice(quote);
                    return new Quote(quote,stock.getQuote().getPrice());
                })
                .collect(Collectors.toList());
    }

    private Stock getStockPrice(String s) {
        try {
            return YahooFinance.get(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class Quote {
        String quote;
        BigDecimal price;
    }
}
