package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGetAndExtractResponseOption3 {

    /* option 3 */
    @Test
    public void extract_Response(){
        String response = given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response().asString();
        System.out.println("workspaces name="+ JsonPath.from(response).getString("workspaces[1].name"));
    }
}
