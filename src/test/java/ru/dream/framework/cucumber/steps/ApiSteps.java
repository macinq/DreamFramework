package ru.dream.framework.cucumber.steps;

import io.cucumber.java.ru.Дано;
import ru.dream.framework.api.Request;

public class ApiSteps {
    @Дано("отпрака запроса по эндпоиту {string}")
    public void getRequest(String arg) {
        Request request = new Request(arg);
    }
}
