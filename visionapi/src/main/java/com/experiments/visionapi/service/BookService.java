package com.experiments.visionapi.service;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class BookService {

    public void findBookDetails(AnnotateImageResponse imageResponse) throws IOException {

//        String fullText = imageResponse.getFullTextAnnotation().getText();
//        String potentialText = fullText.indexOf("","",)

        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "searchString";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        // Show title and URL of 1st result.
        System.out.println(results.getResponseData().getResults().get(0).getTitle());
        System.out.println(results.getResponseData().getResults().get(0).getUrl());
    }
}
