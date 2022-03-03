package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.simple.pojo.SimplePojo;
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
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoDeserialize {

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
        public void simple_Pojo_Deserialise() throws JsonProcessingException {
             SimplePojo simplePojo=new SimplePojo("value1","value2");


            SimplePojo deserializedPojo = given() /* here deserialized pojo is there */
                    .body(simplePojo)
                    .when()
                    .post("/postsimplepojo")
                    .then().spec(responseSpecification)
                    .extract()
                    .response()
                    .as(SimplePojo.class); /* this is converting serialized pojo to deserialized pojo */

            ObjectMapper objectMapper=new ObjectMapper();
            String deserialisePojoStr = objectMapper.writeValueAsString(deserializedPojo);/* here we are serializing from deserializing */
            String simplePojoStr = objectMapper.writeValueAsString(simplePojo); /* here we are serializing from deserializing */
            assertThat(objectMapper.readTree(deserialisePojoStr),equalTo(objectMapper.readTree(simplePojoStr))); /* here we are asserting complete response with the complete request */

            /*read tree() from object mapper will read the serialise pojo */
        }
}
