package com.game.mancala.kalah.presentation.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Response class that builds the response.
 */
@Getter
@JsonInclude()
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveResponse {

    /**
     * The id of the game.
     */
    private String id;

    /**
     * The request url
     */
    private String url;

    /**
     * The current state of the game pits
     */
    private Map<Integer, Integer> status = new HashMap<>();

    public MoveResponse(String id, String url, Integer[] pitInformation) {
        this.id = id;
        this.url = url;

        for(int i=0;i<pitInformation.length;i++){
            status.put(i+1, pitInformation[i]);
        }
    }
}
