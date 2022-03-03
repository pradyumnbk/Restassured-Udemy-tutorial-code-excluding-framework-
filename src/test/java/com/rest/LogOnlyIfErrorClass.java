package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class LogOnlyIfErrorClass {
    @Test
    public void log_only_If_Error(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200);
    }
}
/* log().ifError() will print the error response only. */