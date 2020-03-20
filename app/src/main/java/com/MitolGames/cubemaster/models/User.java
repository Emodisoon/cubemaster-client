package com.MitolGames.cubemaster.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("userName")
    String userName;
    @JsonProperty("id")
    String id;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
