package com.rest;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class AutomateGet {

    @Test
    public void validate_Get_Status_code() {
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")

                .when()
                .get("/workspaces")

                .then()
                .log().all() //----> if we use this method here then it will print the response if assertion fail also
                .assertThat()
                .statusCode(200)
                 //.log().all();//if we will use this method here and if it is error then response will not get printed.
                .body("workspaces.name",hasItems("Team Workspace", "open-weather", "GITHUB_API", "My Workspace"),
                        "workspaces.type", hasItems("team", "team", "team", "personal"),
                        "workspaces[0].name",is(equalTo("Team Workspace"))
                      ,"workspaces.size()",equalTo(4)
                ,"workspaces.name", hasItem("My Workspace"));//this is groovy path.here is method is used for beautifying. equal to method is used to compare one operand
                        //size() will calculate size of the workspaces
    }
}
