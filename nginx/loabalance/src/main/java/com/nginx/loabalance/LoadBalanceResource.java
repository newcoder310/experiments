package com.nginx.loabalance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadBalanceResource {

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader Map<String, String> headers) {
        headers.forEach((key,value) -> {
            System.out.println(key+" -> "+value);
        });
        return ResponseEntity.ok("ok");
    }
}
