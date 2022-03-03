package com.rest;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AutomateGetAndExtractResponseOption4 {
    /* option 4 */
    @Test
    public void extract_Response(){
        String name = given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .when()
                .get("/workspaces")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .response().path("workspaces[1].name");
        System.out.println("workspaces name="+name);
        /* hamcrest assertion for assertion  */
        //assertThat(name,equalTo("open-weather"));

        /* testng assertion */
        Assert.assertEquals(name,"open-weather");
    }
}
