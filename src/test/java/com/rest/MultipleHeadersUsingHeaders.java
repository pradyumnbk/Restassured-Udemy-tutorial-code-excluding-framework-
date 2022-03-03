package com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MultipleHeadersUsingHeaders {
    @Test
    public void multiple_Headers_Using_Headers(){
        Header header=new Header("headerName","value2");
        Header matchHeader=new Header("x-mock-match-request-headers","headerName");
        Headers headers=new Headers(header,matchHeader);
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
