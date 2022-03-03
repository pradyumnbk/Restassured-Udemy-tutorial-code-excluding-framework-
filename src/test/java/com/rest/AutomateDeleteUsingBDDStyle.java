package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;

public class AutomateDeleteUsingBDDStyle {
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
    public void automate_Put_Using_BDD_Style(){
        String workspaceId="a0753bdb-22fd-48c7-9a06-437a567474c5";

        given()
                .pathParam("workspaceId",workspaceId)
                .when()
                .delete("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .assertThat().body("workspace.id",matchesRegex("^[a-z0-9-]{36}$"),
                        "workspace.id",equalTo(workspaceId));
    }
}
