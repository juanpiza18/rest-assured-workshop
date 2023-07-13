package com.restassured.test;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredAuthTest extends BaseClassAuth{

    @Test
    public void test1() {
        int statusCode = given().
                get().
                getStatusCode();
        System.out.println("Response code form server is " + statusCode);
        Assert.assertEquals(statusCode ,200);
    }

}
