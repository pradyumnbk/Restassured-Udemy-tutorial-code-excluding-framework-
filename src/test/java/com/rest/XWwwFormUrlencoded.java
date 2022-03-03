package com.rest;
import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XWwwFormUrlencoded {
     @Test
    public void x_Www_Form_Urlencoded(){
         given()
                         .formParam("foo1","bar1")
                         .formParam("foo 1","bar 1")
                 /*in the header it is showing application/x-www-form-urlencoded; charset=ISO-8859-1, but
                  in postman charset=ISO-8859-1 is not there in the header content type, so we have to
                  tell rest assured  not to use charset=ISO-8859-1 */
                 .config(config.encoderConfig(EncoderConfig.encoderConfig()
                         .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri("https://postman-echo.com")
                 .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                         .assertThat()
                         .statusCode(200);
    }
}
