package com.rest;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Log_Blacklist_Headers {
    @Test
    public void log_Blacklist_Headers(){
        Set<String> headers=new HashSet<String>();
        headers.add("X-Api-Key");
        headers.add("accept");
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key","PMAK-61e137f77d7bd674ab11ef87-bbd19100363c2ad6265d6f58ad05fbb878")
                //.config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key")))
                /*if we want to blacklist multiple headers then we will use .blacklistheader(collection<String>)*/
                .config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers)))
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
