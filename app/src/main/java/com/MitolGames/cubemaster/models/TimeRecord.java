package com.MitolGames.cubemaster.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;


public class TimeRecord {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("time")
    private long time;
    @JsonProperty("scramble")
    private String scramble;
    @JsonProperty("creationDate")
    private String creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
