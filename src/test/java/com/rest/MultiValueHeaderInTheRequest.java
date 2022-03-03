package com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MultiValueHeaderInTheRequest {
    @Test
    public void multiValue_Header_In_The_Request(){
        /*another way*/
        Header header1=new Header("multivalueheader","value1");
        Header header2=new Header("multivalueheader","value2");
        Headers headers=new Headers(header1,header2);
        given()
                .baseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                /*one way*/
                //.header("multivalueheader","value1","value2")
                /*another way*/
                .headers(headers)
                .log().headers()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
