package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ValidatePostRequestPayloadAsMap {
    @BeforeClass
    public void before_Class(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification= responseSpecBuilder.build();
    }

    @Test
    public void validate_Post_Request_Payload_As_Map() throws JsonProcessingException {
        /* In order to use hashmap as a body we need to serialize the java object to json object for that we need jackson data-bind dependency */
        HashMap<String,Object> mainObject=new HashMap<String,Object>();
        HashMap<String,String> nestedObject=new HashMap<String,String>();
        nestedObject.put("name","myFirstWorkspace12");
        nestedObject.put("type","personal");
        nestedObject.put("description","Rest Assured created this");

        mainObject.put("workspace",nestedObject);

        /* In order to serialize explicitly without using jackson databind then we have to
          serialize explicitly using ObjectMapper class of jackson api */
        ObjectMapper objectMapper=new ObjectMapper();
        String mainObjectString = objectMapper.writeValueAsString(mainObject);

        given()
                //.body(mainObject)/* for implicitly serialize using jackson data-bind */
                .body(mainObjectString)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("myFirstWorkspace12"),
                        "workspace.id",matchesRegex("^[a-z0-9-]{36}$"));
    }

    @Test
    public void serialize_Map_To_Json_Using_Jackson_Object_Node() throws JsonProcessingException {
        /* Here we are serializing based on object node as it is similar to hashmap */
        ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode nestedObjectNode = objectMapper.createObjectNode();
        nestedObjectNode.put("name","myFirstWorkspace12");
        nestedObjectNode.put("type","personal");
        nestedObjectNode.put("description","Rest Assured created this");

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspace",nestedObjectNode);

        /* In order to serialize explicitly without using jackson databind then we have to
          serialize explicitly using ObjectMapper class of jackson api */

        String mainObjectString = objectMapper.writeValueAsString(mainObjectNode);

        given()
                //.body(mainObjectString)
                /* If we want to use mainObjectNode directly then also we can use it */
                .body(mainObjectNode)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("myFirstWorkspace12"),
                        "workspace.id",matchesRegex("^[a-z0-9-]{36}$"));
    }
}
