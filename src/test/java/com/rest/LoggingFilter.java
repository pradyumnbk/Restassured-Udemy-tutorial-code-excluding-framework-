package com.rest;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingFilter {
    @Test
    public void logging_Filter(){
        /*Instead of using log().all() we can use filters to log the request and response payload */
        given()
                .baseUri("https://postman-echo.com")
                //.filter(new RequestLoggingFilter())
                //.filter(new ResponseLoggingFilter())
                /*if we want to log specific thing then we can use this */
                .filter(new RequestLoggingFilter(LogDetail.BODY))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                .when()
                .get("/get")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200);
    }
}
