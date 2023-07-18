package com.restassured.examples;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExamples {

    @Test
    public void testGet(){
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2").
        then()
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Rachel"));
}

    @Test
    public void testPost() {
        baseURI = "https://reqres.in/api";
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("name", "Juan");
        // map.put("job", "Developer");

        // System.out.println(map);
        JSONObject payload = new JSONObject();
        payload.put("name", "Juan");
        payload.put("job", "Developer");
        System.out.println(payload);

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(payload.toJSONString())
        .when()
                .post("/users")
        .then()
                .statusCode(201).log().all();
    }
}
