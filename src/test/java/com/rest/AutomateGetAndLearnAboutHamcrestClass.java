package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutomateGetAndLearnAboutHamcrestClass {

    @Test
    public void validateResponseBodyHamcrestLearning(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .statusCode(200)
                //here hamcrest contains() will do strict assertion. It will check every string in it and if it is matched then only testcase will pass.
                //but in hasItems() even if we provide two elements it will pass
               // .body("workspaces.name", contains("Team Workspace", "open-weather", "GITHUB_API", "My Workspace"));
                 //but if order is not important then we can go for containInAnyOrder()
                //.body("workspaces.name",containsInAnyOrder("open-weather","Team Workspace","My Workspace","GITHUB_API"));
                //if we want to check if the collection is empty or not. Then we will use empty()
                //.body("workspaces.name",empty());
                 //if we want to check if the collection is not empty then we will use (is(not(empty())))
                //.body("workspaces.name",is(not(empty())));
                //if you want to check the size of the collection then we will use hasSize()
                //.body("workspaces.name",hasSize(3));
                //if we want to check if the name in the collection starts with specific text then
                .body("workspaces.name",everyItem(startsWith("Team")));
    }
}
