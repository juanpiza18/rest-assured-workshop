package com.restassured.test;

import com.restassured.pojo.CreatedStudent;
import com.restassured.pojo.Student;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StudentsFeature extends AuthWithSessionTokenParseApi {

    @Test
    public void create_user() {
        String studentRandomString = RandomStringUtils.random(6, true, false);
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName(studentRandomString);
        student.setLastname(studentRandomString);
        student.setInterests(interest);

        given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(201)
                .body("objectId", notNullValue())
                .body("createdAt", notNullValue());

        when()
                .get("/classes/Students")
                .then()
                .statusCode(200)
                .body("results.name", hasItem(studentRandomString));
    }

    @Test
    public void not_allowed_to_create_user_with_same_name_and_lastname() {
        String studentRandomString = RandomStringUtils.random(6, true, false);
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName(studentRandomString);
        student.setLastname(studentRandomString);
        student.setInterests(interest);

        given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(201);

        given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(400)
                .body("error", is("A duplicate value for a field with unique values was provided"));
    }


    @Test
    public void not_allowed_to_special_characters_in_name_and_lastname() {
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName("123131$%&");
        student.setLastname("lastname");
        student.setInterests(interest);

        given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(400)
                .body("code", is("Invalid characters on field \"name\""));

        student.setName("valid");
        student.setLastname("123131$%&");

        given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(400)
                .body("code", is("Invalid characters on field \"lastname\""));
    }

    @Test
    public void update_interest_created_user() {
        String studentRandomString = RandomStringUtils.random(6, true, false);
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName(studentRandomString);
        student.setLastname(studentRandomString);
        student.setInterests(interest);

        CreatedStudent response =
                given()
                        .body(student)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/classes/Students")
                        .then()
                        .statusCode(201)
                        .extract().body().as(CreatedStudent.class);

        Student student1 = new Student();
        List<String> newInterest = new ArrayList<>();
        newInterest.add("Football");
        student1.setInterests(newInterest);

        given()
                .header("Content-Type", "application/json")
                .pathParams("studentId", response.getObjectId())
                .body(student1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("classes/Students/{studentId}")
                .then()
                .statusCode(200)
                .body("updatedAt", notNullValue());
    }
}
