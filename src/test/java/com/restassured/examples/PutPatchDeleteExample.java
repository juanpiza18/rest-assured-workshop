package com.restassured.examples;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PutPatchDeleteExample {
    @Test
    public void testPut(){
        baseURI = "https://reqres.in/api";
        JSONObject payload = new JSONObject();
        payload.put("name", "Juan");
        payload.put("job", "Developer");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(payload.toJSONString())
        .when()
                .put("/users/2")
        .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testPatch(){
        baseURI = "https://reqres.in/api";
        JSONObject payload = new JSONObject();
        payload.put("job", "Developer");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(payload.toJSONString())
        .when()
                .patch("/users/2")
        .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testDelete(){
        baseURI = "https://reqres.in/api";
        when()
                .delete("/users/2")
        .then()
                .statusCode(204).log().all();
    }
}
