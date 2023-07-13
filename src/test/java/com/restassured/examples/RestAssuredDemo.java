package com.restassured.examples;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredDemo extends Base {

    @Test
    public void testAuth1() {
       /* baseURI = "https://postman-echo.com/basic-auth";
        RequestSpecification request = given();

        Response response = request.get();
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Status message " + response.body().asString());*/
        int statusCode = given().when().get().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
