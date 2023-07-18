package com.restassured.examples;

import com.restassured.examplespojo.ListUserPojo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DeserializationTest {
    @Test
    public void deserializationTest_1() {
        baseURI = "https://reqres.in/api";
        ListUserPojo responseList =  given().when().get("/users").as(ListUserPojo.class);
        System.out.println(responseList.toString());
    }
}
