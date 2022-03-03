package com.rest;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Log_Only_If_Validation_Failed {
    @Test
    public void log_Only_If_Validation_Failed(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                //.log().ifValidationFails() //it will print the log only if validation fails
                .when()
                .get("/workspaces")
                .then()
                //.log().ifValidationFails()  //it will print the log only if validation fails
                .assertThat()
                .statusCode(201);
    }
}
/* if we dont want to write .log().ifValidationFails() in both request and response then we will use .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))*/