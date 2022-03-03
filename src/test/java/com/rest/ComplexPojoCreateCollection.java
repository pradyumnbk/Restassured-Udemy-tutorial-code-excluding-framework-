package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.complex.pojo.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ComplexPojoCreateCollection {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .setContentType("application/json")
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void complex_Pojo() throws JsonProcessingException, JSONException {
        Header header=new Header("Content-Type","application/json");
        List<Header> headerList=new ArrayList<Header>();
        headerList.add(header);
        Body body=new Body("raw","{\"data\":\"123\"}");
        Request request=new Request("https://postman-echo.com/post","POST",headerList,body);
        RequestRoot  requestRoot=new RequestRoot("sample POST Request",request);
        List<RequestRoot> requestRootList=new ArrayList<>();
        requestRootList.add(requestRoot);
        Folder folder=new Folder("This is a folder",requestRootList);
        Info info=new Info("Sample Collection","This is just a sample collection."
                ,"https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        List<Folder> folderList=new ArrayList<Folder>();
        folderList.add(folder);
        Collection collection=new Collection(info,folderList);
        RootCollection rootCollection=new RootCollection(collection);
        String collectionUid=given()
                .body(rootCollection)
                .when()
                .post("/collections")
                .then().spec(responseSpecification)
                .extract()
                .response()
                .path("collection.uid");
        /* here get call response is used as a request post call request */

        RootCollection deserializedRootCollection = given()
                .pathParam("collectionUid", collectionUid)
                .when()
                .get("/collections/{collectionUid}")
                .then().spec(responseSpecification)
                .extract()
                .response()
                .as(RootCollection.class);

        ObjectMapper objectMapper=new ObjectMapper();
        String deserializedRootCollectionStr = objectMapper.writeValueAsString(deserializedRootCollection);
        String rootCollectionStr = objectMapper.writeValueAsString(rootCollection);


        JSONAssert.assertEquals(rootCollectionStr,deserializedRootCollectionStr,
                new CustomComparator(JSONCompareMode.STRICT,
                new Customization("collection.item[*].item[*].request.url",
                        new ValueMatcher<Object>() {
                            public boolean equal(Object o1, Object o2) {
                                return true;
                            }
                        })));
    }
}
