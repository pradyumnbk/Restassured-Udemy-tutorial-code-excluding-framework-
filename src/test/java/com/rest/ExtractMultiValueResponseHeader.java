package com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ExtractMultiValueResponseHeader {
    @Test
    public void extract_MultiValue_Response_Header(){
        Header header=new Header("headerName","value1");
        Header matchHeader=new Header("x-mock-match-request-headers","headerName");
        Headers headers=new Headers(header,matchHeader);
        Headers extractHeaders = given()
                .baseUri("https://252f8416-7cdc-4095-8391-62d7538527d2.mock.pstmn.io")
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .headers();
        List<String> values = extractHeaders.getValues("multiValueHeader");
        for (String value:values) {
            System.out.print(value);
        }
    }
}
