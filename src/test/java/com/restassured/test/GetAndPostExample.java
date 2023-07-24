package com.restassured.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAndPostExample {
    @Test
    public void testGetReqresAPI() {
        baseURI = "https://reqres.in/api";
        when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data.size()", is(6))
                .body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testPostReqresAPI() {

        JSONObject request = new JSONObject();

        request.put("name", "Ernesto Perez");
        request.put("job", "QA Automation");

        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201);
    }

    @Test
    public void testGetPokeAPI() {
        baseURI = "https://pokeapi.co/api/v2";
        // Option 1
        when()
                .get("/pokemon/ditto")
                .then()
                .statusCode(200);
        //.log().all();
        // option 2
        Response response = get("/pokemon/ditto");
        Assert.assertEquals(response.statusCode(), 200);
        // System.out.println(response.getBody().asString());

    }
}
