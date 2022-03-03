package com.rest;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LogToFile {
    @Test
    public void log_To_File() throws FileNotFoundException {
        /*If we want to log the request and response log into a log file then we will use this steps */
        PrintStream fileOutputStream=new PrintStream(new File("Restassured.log"));
        given()
                .baseUri("https://postman-echo.com")
               // .filter(new RequestLoggingFilter(fileOutputStream))
               // .filter(new ResponseLoggingFilter(fileOutputStream))
                /*if we want to log the specific thing then we will use this thing */
                .filter(new RequestLoggingFilter(LogDetail.BODY,fileOutputStream))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS,fileOutputStream))
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
