package com.restassured.test;

import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SkillsFeature extends AuthWithoutTokenParseApi {
    @Test
    public void list_available_skills_in_system() {
        when()
                .get("classes/Skills")
        .then()
                .statusCode(200)
                .body("results.size()", greaterThan(0))
                .body("results[0].name", is("Functional testing"));
    }

    @Test
    public void get_skill_by_code_id() {
        when()
                .get("classes/Skills/ODp5lTet6R")
        .then()
                .statusCode(200)
                .body("name", notNullValue())
                .body("description", notNullValue())
                .body("objectId", notNullValue());
    }

    @Test
    public void delete_skill_is_not_allowed() {
        when()
                .delete("classes/Skills/ODp5lTet6R")
        .then()
                .statusCode(400)
                .body("error", is("Permission denied for action delete on class Skills."));
    }

    @Test
    public void request_should_contain_valid_headers() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("application", "notvalid");
        builder.addHeader("api", "notvalid");

        given()
                .spec(builder.build())
        .when()
                .get("classes/Skills")
        .then()
                .statusCode(401)
                .body("error", is("unauthorized"));
    }
}
