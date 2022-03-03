package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MultipartFormData {
    @Test
    public void multipart_Form_Data(){
                 given()
                .baseUri("https://postman-echo.com")
                         .multiPart("foo1","bar1")
                         .multiPart("foo2","bar2")
                .log().all()
                         .when()
                         .post("/post")
                         .then()
                         .log().all()
                         .assertThat()
                         .statusCode(200);
    }
}
