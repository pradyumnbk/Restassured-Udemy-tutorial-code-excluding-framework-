package nonbddstylerestassured;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class RequestSpecificationExample {
    RequestSpecification requestSpecification;
    @BeforeClass
    public void before_Class(){
         requestSpecification=given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878");
    }
    @Test
    public void validate_Status_code(){
        given(requestSpecification)
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void validate_Response_Body(){
        given(requestSpecification)
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces[0].name",is(equalTo("Team Workspace")));
    }
}
