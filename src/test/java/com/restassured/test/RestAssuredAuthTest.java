package com.restassured.test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredAuthTest extends BaseClassAuth{

    @Test(priority = 0, description="Valid Authentication Scenario with valid username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with valid username and password.")
    @Story("Get authentication token")
    @Step("Petition get to authentication")
    public void test1() {
        int statusCode = given().
                get().
                getStatusCode();
        System.out.println("Response code form server is " + statusCode);
        Assert.assertEquals(statusCode ,200);
    }

    @Test
    public void test2WithHamcrestValidation() {
        given()
                .get()
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

}
