package com.restassured.test;

import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class AuthWithoutTokenParseApi {

    @BeforeClass
    public void setup() {
        baseURI = "https://parseapi.back4app.com/";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("X-Parse-Application-Id", System.getenv("REST_APP_ID"));
        builder.addHeader("X-Parse-REST-API-Key", System.getenv("REST_APP_KEY"));
        requestSpecification = builder.build();
    }

    @AfterClass
    public void tearDown() {
        reset();
    }
}
