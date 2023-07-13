package com.restassured.examples;


import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class Base {
    @BeforeClass
    public void setup() {
        authentication = preemptive().basic("postman", "password");
        baseURI = "https://postman-echo.com/basic-auth";
    }
}
