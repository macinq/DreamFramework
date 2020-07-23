package ru.dream.framework.cucumber.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    @Дано("отправка запроса по эндпоиту {string} ожидаемый ответ {int}")
    public void getRequest(String arg, int code) {
        given()
                .get(arg)
                .then()
                .statusCode(code);
    }
}
