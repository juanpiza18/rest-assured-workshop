package com.restassured.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.restassured.pojo.utils.BaseDTO;

public class CreatedUser extends BaseDTO {
    private String type;
    private String className;

    @JsonGetter("__type")
    public String getType() {
        return type;
    }

    @JsonSetter("__type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter("className")
    public String getClassName() {
        return className;
    }

    @JsonSetter("className")
    public void setClassName(String className) {
        this.className = className;
    }
}
