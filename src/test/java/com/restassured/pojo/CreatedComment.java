package com.restassured.pojo;

import com.restassured.pojo.utils.BaseDTO;

public class CreatedComment extends BaseDTO {
    private String createdAt;
    private CreatedUser user;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CreatedUser getUser() {
        return user;
    }

    public void setUser(CreatedUser user) {
        this.user = user;
    }
}
