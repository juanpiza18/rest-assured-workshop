package com.restassured.test;

import com.restassured.pojo.Student;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StudentsNoSessionTokenFeature extends AuthWithoutTokenParseApi {

    @Test
    public void no_session_token_set_in_post_request() {
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
                .statusCode(404)
                .body("error", is("Permission denied, user needs to be authenticated."));
    }
}
