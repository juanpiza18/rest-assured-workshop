package com.restassured.pojo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
