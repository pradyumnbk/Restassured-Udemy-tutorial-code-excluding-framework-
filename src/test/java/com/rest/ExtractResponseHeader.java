package com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ExtractResponseHeader {
    @Test
    public void extract_Response_Header(){
        Header header=new Header("headerName","value2");
        Header matchHeader=new Header("x-mock-match-request-headers","headerName");
        Headers headers=new Headers(header,matchHeader);
        Headers extractHeaders = given()
                .baseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();
        /*if we want to print all header name and value we will use for each loop*/
        for (Header impHeader:extractHeaders) {
            System.out.print("response header name="+impHeader.getName()+", ");
            System.out.println("response header value="+impHeader.getValue());
        }
        /*if we want to print only one header*/
        //System.out.println("response header name="+extractHeaders.get("responseHeader").getName());
        //System.out.println("response header value="+extractHeaders.get("responseHeader").getValue());
        //System.out.println("response header value="+extractHeaders.getValue("responseHeader"));
    }
}
