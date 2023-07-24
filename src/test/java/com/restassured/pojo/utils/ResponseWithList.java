package com.restassured.pojo.utils;

import java.util.List;

public interface ResponseWithList<T> {
    List<T> getResults();

    void setResults(List<T> results);
}
