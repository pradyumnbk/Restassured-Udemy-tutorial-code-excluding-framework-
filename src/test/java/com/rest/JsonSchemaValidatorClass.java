package com.rest;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidatorClass {
    @Test
    public void json_Schema_Validator(){
        given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("EchoGet.json"));
    }
}
