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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;

public class AutomatePostUsingNonBddStyle {

    ResponseSpecification responseSpecification;
    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY);
        responseSpecification=responseSpecBuilder.build();
    }
    @Test
    public void validate_Post_Request_NonBDD_Style(){
        String payload="{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"myFirstWorkspace1\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Rest Assured created this\"\n" +
                "    }\n" +
                "}";
        Response response = given()
                .body(payload)
                .post("/workspaces");
        assertThat(response.path("workspace.name").toString(),equalTo("myFirstWorkspace1"));
        assertThat(response.path("workspace.id").toString(),matchesRegex("^[a-z0-9-]{36}$"));
    }
}
