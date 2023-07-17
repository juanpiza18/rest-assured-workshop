package com.restassured.examples;

import com.restassured.examplespojo.Pokemons;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestPathParameters {
    @Test
    public void whenRequestHasParameters() {
        given()
                .pathParams("type", "3")
        .when()
                .get("https://pokeapi.co/api/v2/type/{type}")
        .then()
                .statusCode(200);
    }

    @Test
    public void whenRequestHasParametersLimitAndOffset() {
        given()
                .pathParams("limit", "5", "offset", "0")
        .when()
                .get("https://pokeapi.co/api/v2/pokemon?limit={limit}&offset={offset}")
        .then()
                .statusCode(200)
                .body("results.size()", is(5));
    }

    @Test
    public void whenRequestHasParametersLimitAndOffsetPOJOClass() {
        Pokemons results = given()
                                .pathParams("limit", "5", "offset", "0")
                           .when()
                                .get("https://pokeapi.co/api/v2/pokemon?limit={limit}&offset={offset}")
                                .as(Pokemons.class);
        Assert.assertEquals(results.getResults()[0].getName(), "bulbasaur");
    }

    @Test
    public void whenUseQueryParam_thenOK() {
        given()
                .queryParam("limit", "1", "offset", "0")
        .when()
                .get("https://pokeapi.co/api/v2/pokemon")
        .then()
                .statusCode(200)
                .body("results.size()", is(1));
    }
}
