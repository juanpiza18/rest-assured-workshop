package com.restassured.pojo;

import com.restassured.pojo.utils.BaseDTO;

public class Comment extends BaseDTO {
    private String studentId;
    private String comment;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
