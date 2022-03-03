package nonbddstylerestassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatingResponseSpecification {
    //RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void before_Class() {
        /* we can do method chaining in RequestSpecBuilder */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        responseSpecification = RestAssured.expect()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void validate_Status_Code() {

        get("/workspaces")
                .then().spec(responseSpecification)
                .log().all();
    }
    @Test
    public void validate_Response_Body() {
        Response response = get("/workspaces")
                .then().spec(responseSpecification)
                .log().all()
                .extract()
                .response();
        assertThat(response.path("workspaces[0].name").toString(), equalTo("Team Workspace"));
    }
}