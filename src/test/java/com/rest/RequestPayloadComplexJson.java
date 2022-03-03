package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestPayloadComplexJson {
    ResponseSpecification customResponseSpecification;

    @BeforeClass
    public void before_Class(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .addHeader("x-mock-match-request-body", "true")
                /*if we want to set encoding format along with content type*/
                .setContentType("application/json;charset=utf-8")
                /*If we don't want to set the default encoding format from restassured then we can disable it */
                /*.setConfig(config.encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .setContentType(ContentType.JSON)*/
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        customResponseSpecification= responseSpecBuilder.build();
    }
    @Test
    public void validate_Post_Request_Payload_JsonArray_Aa_List(){
        List<Integer> batterIdArrayList2=new ArrayList<Integer>();
        batterIdArrayList2.add(5);
        batterIdArrayList2.add(9);

        HashMap<String,Object> batterHashMap2=new HashMap<String,Object>();
        batterHashMap2.put("id",batterIdArrayList2);
        batterHashMap2.put("type","Chocolate");

        HashMap<String,Object> batterHashMap1=new HashMap<String,Object>();
        batterHashMap1.put("id","1001");
        batterHashMap1.put("type","Regular");

        List<HashMap<String,Object>> batterArrayList=new ArrayList<HashMap<String,Object>>();
        batterArrayList.add(batterHashMap1);
        batterArrayList.add(batterHashMap2);

        HashMap<String,List<HashMap<String,Object>>> batterHashMap=new HashMap<String,List<HashMap<String,Object>>>();
        batterHashMap.put("batter",batterArrayList);

        List<String> toppingArrayList2=new ArrayList<String>();
        toppingArrayList2.add("test1");
        toppingArrayList2.add("test2");

        HashMap<String,Object> toppingHashMap2 =new HashMap<String,Object>();
        toppingHashMap2.put("id","5002");
        toppingHashMap2.put("type",toppingArrayList2);

        HashMap<String,Object> toppingHashMap1=new HashMap<String,Object>();
        toppingHashMap1.put("id","5001");
        toppingHashMap1.put("type","None");

        List<HashMap<String,Object>> toppingArrayList= new ArrayList<HashMap<String,Object>>();
        toppingArrayList.add(toppingHashMap1);
        toppingArrayList.add(toppingHashMap2);

        HashMap<String,Object> mainHashMap=new HashMap<String,Object>();
        mainHashMap.put("id","0001");
        mainHashMap.put("type","donut");
        mainHashMap.put("name","Cake");
        mainHashMap.put("ppu",0.55);
        mainHashMap.put("batters",batterHashMap);
        mainHashMap.put("topping",toppingArrayList);

        given()
                .body(mainHashMap)
                .when()
                .post("/postComplexJson")
                .then().spec(customResponseSpecification)
                .assertThat().body("msg",equalTo("success"));
    }
}
