package com.restassured.pojo;

import com.restassured.pojo.utils.ResponseWithList;

import java.util.List;

public class Students implements ResponseWithList<Student> {
    private List<Student> results;

    @Override
    public List<Student> getResults() {
        return results;
    }

    @Override
    public void setResults(List<Student> results) {
        this.results = results;
    }
}
