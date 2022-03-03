package com.rest;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutomateGetAndLearnAboutHamcrestClass1 {
    @Test
    public void validateResponseBodyAndLearnAboutHamcrestClass(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                //hasKey() will check if the particular string contain mentioned key or not
                //.body("workspaces[0]",hasKey("id"));
                //if we want to check the value in the particular string then we will use hasValue()
                //.body("workspaces[0]",hasValue("4f0ca6fe-f90e-4a60-8a0e-83da7d602b81"));
                //if we want to check the both key and value pair in the string then we will use hasEntry()
                //.body("workspaces[0]",hasEntry("name","Team Workspace"));
                //if you want to check if the collection is empty or not then we will use equalTo(Collections.emptyMap())
                // .body("workspaces[0]",not(equalTo(Collections.emptyMap())));
                //allOf() will work on string
                .body("workspaces[0].name",allOf(startsWith("Team"),containsString("Workspace")));
    }
}
