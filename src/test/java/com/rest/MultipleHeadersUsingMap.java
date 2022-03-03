package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MultipleHeadersUsingMap {
    @Test
    public void multiple_Headers_Using_Map(){
        HashMap<String,String> headers=new HashMap<String,String>();
        headers.put("headerName","value2");
        headers.put("x-mock-match-request-headers","headerName");
        given()
                .baseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
