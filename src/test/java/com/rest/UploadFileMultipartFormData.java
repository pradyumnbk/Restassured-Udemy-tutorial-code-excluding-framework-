package com.rest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UploadFileMultipartFormData {
    @Test
    public void upload_File(){
        String attribute="{\"name\":\"xpath.txt\",\"parent\":{\"id\":\"12345\"}}";
        given()
                .baseUri("https://postman-echo.com")
                .multiPart("file",new File("xpath.txt"))
                .multiPart("attributes",attribute,"application/json")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
