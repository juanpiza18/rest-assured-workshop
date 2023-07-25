package com.restassured.pojo;

import com.restassured.pojo.utils.ResponseWithList;

import java.util.List;

public class Comments implements ResponseWithList<Comment> {
    private List<Comment> results;

    @Override
    public List<Comment> getResults() {
        return results;
    }

    @Override
    public void setResults(List<Comment> results) {
        this.results = results;
    }
}
