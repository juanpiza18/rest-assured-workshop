package com.restassured.test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StudentSkillsNoSessionTokenFeature extends AuthWithoutTokenParseApi {

    @Test(priority = 0, description="Verify that in order to associate new skills you must be authenticated in the system")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: should display a message saying that the user must be authenticated")
    public void verify_authentication_is_needed_to_associate_new_skills() {
        JSONObject payload = new JSONObject();
        payload.put("studentId", "studentId");
        payload.put("skillId", "skillId");

        given()
                .body(payload)
                .contentType(ContentType.JSON)
        .when()
                .post("/classes/StudentSkills")
        .then()
                .statusCode(404)
                .body("error", is("Permission denied, user needs to be authenticated."));
    }
}
