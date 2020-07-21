package ru.dream.framework.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {
    private String endpoint;

    public Request(String endpoint) {
        this.endpoint = endpoint;
    }

    @BeforeClass
    public void setUp() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("settings.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(System.getProperty("client.url") + System.getProperty("api.prefix"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public void apply() {
        Map<String, Integer> inventory = given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().body()
                .jsonPath()
                .getMap("");

        Assert.assertTrue(inventory.containsKey("sold"), "Inventory не содержит статус sold" );
    }
}
