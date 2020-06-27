package com.experiments.visionapi.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Builder
@Getter
@Setter
public class GoogleResults {

    private ResponseData responseData;
    public String toString() { return "ResponseData[" + responseData + "]"; }

    @Getter
    @Setter
    static class ResponseData {
        private List<Result> results;
        public String toString() { return "Results[" + results + "]"; }
    }

    @Getter
    @Setter
    static class Result {
        private String url;
        private String title;
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }

}