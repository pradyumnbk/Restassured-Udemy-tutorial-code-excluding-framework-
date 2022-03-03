package com.rest;

import io.restassured.http.Header;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateHeaderByCreatingMockApi {
     @Test
    public void multiple_Header(){
         /* we can pass header by creating the object of header class*/
         Header header=new Header("headerName","value1");
         Header matcher=new Header("x-mock-match-request-headers","headerName");
        given()
                .baseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                //.header("headerName","value1")
                //.header("x-mock-match-request-headers","headerName")
                /*another way of passing header*/
                .header(header)
                .header(matcher)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
