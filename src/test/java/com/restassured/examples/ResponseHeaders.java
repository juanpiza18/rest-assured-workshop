package com.restassured.examples;

import static io.restassured.RestAssured.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResponseHeaders {
    @Test
    public void iteratingHeaders() {
        baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            System.out.println("Key " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void GetBookHeaders() {
        baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);
        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);
        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + acceptLanguage);
    }

    @Test
    public void GetBookHeadersAssertions() {
        baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get();
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");
        /*Assert.assertEquals(contentType, "application/XML charset=utf-8");*/
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.17.10 (Ubuntu)");
    }
}
