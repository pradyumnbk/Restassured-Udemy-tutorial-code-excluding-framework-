package com.rest;

import com.rest.Pojo.assignment.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PojoAssignment {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType("application/json")
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void complex_Pojo_Assignment(){
        Address address=new Address("Kulas Light","Apt. 556","Gwenborough","92998-3874");

        Geo geo=new Geo("-37.3159","81.1496");

        User user=new User("Leanne Graham","Bret","Sincere@april.biz",address,geo);

        User deserializedUser = given()
                .body(user)
                .when()
                .post("/users")
                .then().spec(responseSpecification)
                .extract()
                .response()
                .as(User.class);
                assertThat(deserializedUser.getId(),notNullValue());
    }

}
