package nonbddstylerestassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecBuilderClass {
    @BeforeClass
    public void before_Class() {
        /* we can do method chaining in RequestSpecBuilder and ResponseSpecBuilder */
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification=responseSpecBuilder.build();

    }
    @Test
    public void validate_Status_Code() {
        get("/workspaces");

    }
    @Test
    public void validate_Response_Body() {
        Response response = get("/workspaces")
                .then()
                .extract()
                .response();
        assertThat(response.path("workspaces[0].name").toString(), equalTo("Team Workspace"));
    }
}