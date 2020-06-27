package com.experiments.visionapi.controller;

import com.experiments.visionapi.service.BookService;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class VisionController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String readQuestionFromImage(){
        Resource imageResource = this.resourceLoader.getResource("file:src/main/resources/test.png");
        AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(
                imageResource, Feature.Type.DOCUMENT_TEXT_DETECTION);
        //String text = this.cloudVisionTemplate.extractTextFromImage(imageResource);
        return response.getFullTextAnnotation().getText();
    }

}
