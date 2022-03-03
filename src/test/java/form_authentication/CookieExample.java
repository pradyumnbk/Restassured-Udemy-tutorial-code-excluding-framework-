package form_authentication;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieExample {

    @BeforeClass
    public void beforeClass(){
        RestAssured.requestSpecification=new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()  // this will disable ssl certification
                .setBaseUri("https://localhost:8443")
                .build();
    }
    @Test
    public void form_Authentication_Using_Csrf_Token_Cookie_Example(){
        SessionFilter filter=new SessionFilter(); //this will capture the sessionid
        given().
                auth().form("dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                        .withAutoDetectionOfCsrf()).
                filter(filter).
                log().all().
                when().
                get("/login").
                then().
                log().all().
                assertThat().
                statusCode(200);

        System.out.println("session filter= "+filter.getSessionId());

        /*after authentication then we will go to user profile page, this is the code */
        given()
                .cookie("JSESSIONID",filter.getSessionId())
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                /* html path to do the assertion */
                .body("html.body.div.p",equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }
}
