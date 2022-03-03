package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class ValidatePostRequestPayloadFromFile {

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY);
        RestAssured.responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void validate_Post_Request_Payload_from_File(){
        File file=new File("./src/main/resources/createworkspacepayload.json");
        given()
                .body(file)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("myFirstWorkspace123"),
                        "workspace.id",matchesRegex("^[a-z0-9-]{36}$"));

    }
}
