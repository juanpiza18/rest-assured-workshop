package com.restassured.pojo;

import com.restassured.pojo.utils.ResponseWithList;

import java.util.List;

public class Skills implements ResponseWithList<Skill> {
    private List<Skill> results;

    @Override
    public List<Skill> getResults() {
        return results;
    }

    @Override
    public void setResults(List<Skill> results) {
        this.results = results;
    }
}
