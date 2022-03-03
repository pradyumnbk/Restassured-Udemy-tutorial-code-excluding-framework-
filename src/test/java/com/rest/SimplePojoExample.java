package com.rest;

import com.rest.simple.pojo.SimplePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SimplePojoExample {
    ResponseSpecification responseSpecification;
    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void simple_Pojo(){
       // SimplePojo simplePojo=new SimplePojo("value1","value2");

        /*instead of object creation we can use setter method in pojo class to set the value */
        SimplePojo simplePojo=new SimplePojo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");

        given()
                .body(simplePojo)
                .when()
                .post("/postsimplepojo")
                .then().spec(responseSpecification)
                .assertThat()
                .body("key1", equalTo(simplePojo.getKey1()),
                        "key2",equalTo(simplePojo.getKey2()));
    }

}
