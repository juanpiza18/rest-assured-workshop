package com.restassured.test;

import com.restassured.pojo.Comment;
import com.restassured.pojo.CreatedComment;
import com.restassured.pojo.CreatedStudent;
import com.restassured.pojo.Student;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CommentsFeature extends AuthWithSessionTokenParseApi {
    public CreatedStudent createdStudent;

    @BeforeMethod
    public void test_preparation() {
        String studentRandomString = RandomStringUtils.random(6, true, false);
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName(studentRandomString);
        student.setLastname(studentRandomString);
        student.setInterests(interest);

        createdStudent = given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(201)
                .extract().body().as(CreatedStudent.class);
    }

    @Test(description = "Verify that comments can be added to students")
    public void verify_comments_can_be_added_to_students() {
        Comment comment = new Comment();
        comment.setComment("New Comment");
        comment.setStudentId(createdStudent.getObjectId());

        given()
                .body(comment)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Comments")
                .then()
                .statusCode(201)
                .body("objectId", notNullValue())
                .body("createdAt", notNullValue())
                .body("user.objectId", notNullValue())
                .extract()
                .body().as(CreatedComment.class);

        when()
                .get("/classes/Comments")
                .then()
                .statusCode(200)
                .body("results.comment", hasItem("New Comment"));
    }

    @Test(description = "Verify that comments can be deleted")
    public void verify_comments_can_be_deleted() {
        Comment comment = new Comment();
        comment.setComment("Comment To Delete");
        comment.setStudentId(createdStudent.getObjectId());

        CreatedComment createdComment = given()
                .body(comment)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Comments")
                .then()
                .statusCode(201)
                .body("objectId", notNullValue())
                .body("createdAt", notNullValue())
                .body("user.objectId", notNullValue())
                .extract()
                .body().as(CreatedComment.class);

        given()
                .pathParams("commentId", createdComment.getObjectId())
                .when()
                .delete("/classes/Comments/{commentId}")
                .then()
                .statusCode(200);

        when()
                .get("/classes/Comments")
                .then()
                .statusCode(200)
                .body("results.comment", not(hasItem("Comment To Delete")));
    }

    @Test(description = "Verify that comments are associated with the student")
    public void verify_comments_are_associated_with_student() {
        Comment comment = new Comment();
        comment.setComment("Comment Added");
        comment.setStudentId(createdStudent.getObjectId());

        given()
                .body(comment)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Comments")
                .then()
                .statusCode(201);

        given()
                .pathParams("studentId", createdStudent.getObjectId())
        .when()
                .get("/classes/Students/{studentId}")
        .then()
                .statusCode(200)
                .body("comments", hasItem("Comment Added"));
    }
}
