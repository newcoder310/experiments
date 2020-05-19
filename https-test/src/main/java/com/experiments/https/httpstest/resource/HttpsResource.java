package com.experiments.https.httpstest.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/httpsTest")
public class HttpsResource {

    @GetMapping
    public String getResponse(){
        return "hello";
    }


}
