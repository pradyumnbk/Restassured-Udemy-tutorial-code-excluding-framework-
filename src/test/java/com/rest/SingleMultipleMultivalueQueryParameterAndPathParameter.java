package com.rest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SingleMultipleMultivalueQueryParameterAndPathParameter {
    @Test
    public void single_Query_Parameter(){
        given()
                .baseUri("https://postman-echo.com")
                /* we can use param or query param to add query parameter in rest assured */
               // .param("foo1","bar1")
                .queryParam("foo2","bar2")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multiple_Query_Parameter(){
        HashMap<String,String> queryParam=new HashMap<String,String>();
        queryParam.put("foo1","bar1");
        queryParam.put("foo2","bar2");
                 given()
                .baseUri("https://postman-echo.com")
                .queryParams(queryParam)
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void multi_value_Query_Parameter(){
                 given()
                .baseUri("https://postman-echo.com")
               // .queryParam("foo1","bar1,bar2,bar3,bar4")
                         .queryParam("foo1","bar1;bar2;bar3;bar4")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void path_Parameter(){
                 given()
                .baseUri("https://reqres.in")
                         .pathParam("userId",2)
                .log().all()
                .when()
                .get("/api/users/{userId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

}
