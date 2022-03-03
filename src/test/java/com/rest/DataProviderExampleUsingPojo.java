package com.rest;

import com.rest.create.workspace.pojo.RootWorkspace;
import com.rest.create.workspace.pojo.Workspace;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;

public class DataProviderExampleUsingPojo {
    ResponseSpecification responseSpecification;

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
        responseSpecification=responseSpecBuilder.build();
    }
    @Test(dataProvider = "workspace")
    public void serialize_Deserialize_Pojo_DataProvider(String name,String type,String description){
        Workspace workspace=new Workspace(name,type,description);
        RootWorkspace rootWorkspace=new RootWorkspace(workspace);
        RootWorkspace deserialisedRootWorkspace = given()
                .body(rootWorkspace)
                .when()
                .post("/workspaces")
                .then().spec(responseSpecification)
                .extract()
                .response()
                .as(RootWorkspace.class);
        assertThat(deserialisedRootWorkspace.getWorkspace().getName(),
                equalTo(rootWorkspace.getWorkspace().getName()));
        assertThat(deserialisedRootWorkspace.getWorkspace().getId()
                ,matchesRegex("^[a-z0-9-]{36}$"));
    }

    @DataProvider(name="workspace")
    public Object[][] getWorkspace(){
        return new Object[][]{
                {"workspace5","personal","description"},
                {"workspace6","team","description"}
        };
    }
}
