package nonbddstylerestassured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecificationBDDToNonBDDStyle {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void before_Class(){
        requestSpecification=given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .log().all();
    }
    @Test
    public void validate_Status_code(){
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
          assertThat(response.statusCode(),is(equalTo(200)));
    }
    @Test
    public void validate_Response_Body(){
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
        assertThat(response.path("workspaces[0].name").toString(),equalTo("Team Workspace"));
    }
}