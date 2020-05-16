package com.game.mancala.kalah.presentation.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude()
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewGameResponse {

    /**
     * Id of the game created
     */
    private String id;

    /**
     *
     */
    private String url;

    public NewGameResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
