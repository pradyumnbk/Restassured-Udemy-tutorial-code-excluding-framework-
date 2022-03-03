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

public class AutomatePutUsingBDDStyle {

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
        String workspaceId="674339ca-8239-44c6-8620-f1f3cdea6900";
        String payload="{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"My Workspace\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Rest Assured created this\"\n" +
                "    }\n" +
                "}";
        given()
                .pathParam("workspaceId",workspaceId)
                .body(payload)
                .when()
                .put("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("My Workspace")
                ,"workspace.id",matchesRegex("^[a-z0-9-]{36}$"),
                        "workspace.id",equalTo(workspaceId));
    }
}
