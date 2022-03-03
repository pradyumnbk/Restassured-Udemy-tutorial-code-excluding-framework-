package nonbddstylerestassured;

import  io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DefaultRequestSpecification {
/*This way is recommend to use as it is more precise than any other method */
    @BeforeClass
    public void before_Class(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();
    }
    /*when we want to query the request specification and get the information out of it. for example if we want to know
     the base uri or the headers */
    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());
    }
    @Test
    public void validate_Status_code(){
        Response response = get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
    }
    @Test
    public void validate_Response_Body(){
        Response response = get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(),is(equalTo(200)));
        assertThat(response.path("workspaces[0].name"),equalTo("Team Workspace"));
    }
}