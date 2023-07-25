package com.restassured.test;

import com.restassured.pojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class AuthWithSessionTokenParseApi {

    @BeforeClass
    public void setup() {
        baseURI = "https://parseapi.back4app.com/";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("X-Parse-Application-Id", System.getenv("REST_APP_ID"));
        builder.addHeader("X-Parse-REST-API-Key", System.getenv("REST_APP_KEY"));
        Response response =
                given()
                        .spec(builder.build())
                        .queryParams(
                                "username", "testjp001",
                                "password", "testjp001")
                        .header("Content-Type", "application/json")
                        .when()
                        .get("login");
        response.then().statusCode(200);
        User user = response.as(User.class);
        builder.addHeader("X-Parse-Session-Token", user.getSessionToken());
        requestSpecification = builder.build();
    }

    @AfterMethod
    public void methodTearDown() {
        deleteStudents();
        deleteStudents();
    }

    @AfterClass
    public void tearDown() {
        reset();
    }

    public void deleteStudents() {
        Students students = given().when().get("/classes/Students").as(Students.class);
        if (!students.getResults().isEmpty()) {
            int studentsSize = students.getResults().size();
            for (int i = 1; i < studentsSize; i++) {
                Student student = students.getResults().get(i);
                given()
                        .pathParams("studentId", student.getObjectId())
                        .when()
                        .delete("classes/Students/{studentId}")
                        .then()
                        .statusCode(200);
            }
        }
    }

    public void deleteComments() {
        Comments comments = given().when().get("/classes/Comments").as(Comments.class);
        if (!comments.getResults().isEmpty()) {
            int commentsSize = comments.getResults().size();
            for (int i = 0; i < commentsSize; i++) {
                Comment comment = comments.getResults().get(i);
                given()
                        .pathParams("commentId", comment.getObjectId())
                        .when()
                        .delete("/classes/Comments/{commentId}")
                        .then()
                        .statusCode(200);
            }
        }
    }
}
