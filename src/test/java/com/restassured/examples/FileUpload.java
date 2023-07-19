package com.restassured.examples;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;

public class FileUpload {
    @Test
    public void testingUploadFile() {
        File file = new File("src/test/resources/home.jpg");
        Response response =
                given()
                        .multiPart("file", file, "multipart/form-data")
                .when()
                        .post("https://the-internet.herokuapp.com/upload")
                .thenReturn();
        System.out.println(response.prettyPrint());
    }
}
