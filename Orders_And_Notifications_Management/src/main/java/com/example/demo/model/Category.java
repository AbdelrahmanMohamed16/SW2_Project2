package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty("name")
    private String name ;
    @JsonProperty("remainingParts")
    private int remainingParts ;

    public Category(String name, int remainingParts) {
        this.name = name;
        this.remainingParts = remainingParts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getRemainingParts() {
        return remainingParts;
    }

    public void setRemainingParts(int remainingParts) {
        this.remainingParts = remainingParts;
    }
}
