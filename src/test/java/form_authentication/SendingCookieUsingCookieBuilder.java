package form_authentication;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SendingCookieUsingCookieBuilder {
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

        Cookie cookie=new Cookie.Builder("JSESSIONID",filter.getSessionId()).setSecured(true)
                        .setHttpOnly(true).setComment("my cookie").build();
        /*sending multiple cookies*/
        Cookie cookie1=new Cookie.Builder("dummy","dummyValue").build();
        Cookies cookies=new Cookies(cookie,cookie1);
        /*after authentication then we will go to user profile page, this is the code */
        given()
               // .cookie(cookie)
                .cookies(cookies)
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
    @Test
    public void fetch_Single_Cookie(){
        Response response = given()
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.getCookie("JSESSIONID"));
        System.out.println(response.getDetailedCookie("JSESSIONID"));
    }

    @Test
    public void fetch_Multiple_Cookie(){
        Response response = given()
                .log().all()
                .when()
                .get("/profile/index")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

       Map<String,String> cookies=response.getCookies();
       /*Iterating a map*/
        for (Map.Entry<String,String> entry:cookies.entrySet()) {
            System.out.println("cookie name= "+entry.getKey());
            System.out.println("cookie value= "+entry.getValue());
        }
        /*if you want to extract cookie attribute as well then we will use this method*/
        Cookies cookies1 = response.getDetailedCookies();
        List<Cookie> cookieList = cookies1.asList();
        for (Cookie cookie:cookieList) {
            System.out.println("cookie= "+cookie.toString());
        }
    }

}
