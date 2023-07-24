package com.restassured.pojo;

public class CreatedStudent extends BasePojo {
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
