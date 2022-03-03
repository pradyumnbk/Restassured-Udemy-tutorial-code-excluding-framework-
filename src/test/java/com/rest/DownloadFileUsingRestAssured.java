package com.rest;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DownloadFileUsingRestAssured {
  /*  @Test
    public void download_File() throws IOException {
        byte[] bytes = given()
                .baseUri("https://github.com")
                .log().all()
                .when()
                .get("/appium/appium/raw/master/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log().all()
                .extract()
                .response().asByteArray();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();
    }*/
    /*another way using input stream */
    @Test
    public void download_File() throws IOException {
        InputStream is = given()
                .baseUri("https://github.com")
                .log().all()
                .when()
                .get("/appium/appium/raw/master/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log().all()
                .extract()
                .response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));

        byte[] bytes = new byte[is.available()];
        os.write(bytes);
        os.close();
    }
}
