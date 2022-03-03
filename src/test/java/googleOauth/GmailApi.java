package googleOauth;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GmailApi {
    ResponseSpecification responseSpecification;
    String access_Token="ya29.A0ARrdaM8j6OH3mWRDukbamR16CBjIJG5Tw9qiwuuGC7WJiA5DhLS6EPEznAsGH-yARa6XBtx6GRzUIDZiKsAKk-Iinn9-9D6tHwpCT4QyVizN7CKW1Mi-gj1LzkWXzwC9li3SNqYTk56wOuZP3Cls8PxAn0ZE6Q";
    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://gmail.googleapis.com")
                .addHeader("Authorization","Bearer "+access_Token)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void getUserProfile(){
        given()
                .basePath("/gmail/v1")
                .pathParam("userId","pradyumn.barik@aot.edu.in")
                .when()
                .get("/users/{userId}/profile")
                .then().spec(responseSpecification)
                .statusCode(200);
    }
    @Test
    public void sendMessage(){
        String msg="From: pradyumn.barik@aot.edu.in\n" +
                "To: barik.pradyumn0@gmail.com\n" +
                "Subject: Rest assured test Email\n" +
                "\n" +
                "Sending from Rest assured  Gmail API";

        String baseUrlEncodedMsg = Base64.getUrlEncoder().encodeToString(msg.getBytes());
        HashMap<String,String> payload=new HashMap<String,String>();
        payload.put("raw",baseUrlEncodedMsg);

        given()
                .basePath("/gmail/v1")
                .pathParam("userId","pradyumn.barik@aot.edu.in")
                .body(payload)
                .when()
                .post("/users/{userId}/messages")
                .then().spec(responseSpecification)
                .statusCode(200);
    }
    @Test
    public void deleteMessages(){
        given()
                .basePath("/gmail/v1")
                .pathParam("userId","pradyumn.barik@aot.edu.in")
                .pathParam("id","17f3c971afad0c51")
                .when()
                .delete("/users/{userId}/messages/{id}")
                .then().spec(responseSpecification)
                .statusCode(204);
    }
    @Test
    public void getMessage(){
        given()
                .basePath("/gmail/v1")
                .pathParam("userId","pradyumn.barik@aot.edu.in")
                .pathParam("id","17f3c9e460a17934")
                .when()
                .get("/users/{userId}/messages/{id}")
                .then().spec(responseSpecification)
                .statusCode(200);
    }
}
