package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ValidatePostRequestPayloadJsonArrayAsList {
    ResponseSpecification customResponseSpecification;

    @BeforeClass
    public void before_Class(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .addHeader("x-mock-match-request-body", "true")
                /*if we want to set encoding format along with content type*/
                //.setContentType("application/json;charset=utf-8")
                /*If we don't want to set the default encoding format from restassured then we can disable it */
               .setConfig(config.encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        customResponseSpecification= responseSpecBuilder.build();
    }
    @Test
    public void validate_Post_Request_Payload_JsonArray_Aa_List() throws JsonProcessingException {
        HashMap<String,String> obj1001=new HashMap<String,String>();
        HashMap<String,String> obj1002=new HashMap<String,String>();
        obj1001.put("id","1001");
        obj1001.put("type","none");
        obj1002.put("id","1002");
        obj1002.put("type","glazed");
        ArrayList<HashMap<String,String>> payload=new ArrayList<HashMap<String,String>>();
        payload.add(obj1001);
        payload.add(obj1002);

        /*In order to serialize explicitly we use objectmapper class of jackson databind */
        ObjectMapper objectMapper=new ObjectMapper();
        String arrayListStr = objectMapper.writeValueAsString(payload);

                 given()
                .body(arrayListStr)
                .when()
                .post("/post")
                .then().spec(customResponseSpecification)
                .assertThat().body("msg",equalTo("success"));
    }

    @Test
    public void serialize_Json_Array_Using_Jackson_Object_Node() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        ObjectNode obj1001Node = objectMapper.createObjectNode();
        obj1001Node.put("id","1001");
        obj1001Node.put("type","none");

        ObjectNode obj1002Node = objectMapper.createObjectNode();
        obj1002Node.put("id","1002");
        obj1002Node.put("type","glazed");

        ArrayNode arrayNodeList = objectMapper.createArrayNode();
        arrayNodeList.add(obj1001Node);
        arrayNodeList.add(obj1002Node);


        String arrayListStr = objectMapper.writeValueAsString(arrayNodeList);

        given()
                //.body(arrayListStr)
                .body(arrayNodeList)
                .when()
                .post("/post")
                .then().spec(customResponseSpecification)
                .assertThat().body("msg",equalTo("success"));
    }
}