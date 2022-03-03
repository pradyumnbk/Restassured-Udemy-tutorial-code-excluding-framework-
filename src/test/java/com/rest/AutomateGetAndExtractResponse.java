package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGetAndExtractResponse {
    @Test
    public void extract_Response(){
        Response response = given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        /* option 1 */
        //System.out.println("response"+response.asString());//here response is json object, we are converting to string by using asString()
        //here we are extracting the complete request body
        //System.out.println("workspaces name="+response.path("workspaces[0].name"));//here we are extracting single value from the response body

        /* option 2 */
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("workspaces name="+jsonPath.getString("workspaces[1].name"));
    }
}
