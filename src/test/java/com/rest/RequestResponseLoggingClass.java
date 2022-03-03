package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RequestResponseLoggingClass {
    @Test
    public void request_Response_Logging(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                //.log().all()
                .log().headers()
                .when()
                .get("/workspaces")
                .then()
                //.log().all()
                .log().body()
                .assertThat()
                .statusCode(200);
    }
}
/* log().all() will print entire thing contained in the request and response*/