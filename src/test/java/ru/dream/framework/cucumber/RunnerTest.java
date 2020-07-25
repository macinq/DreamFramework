package ru.dream.framework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = "ru.dream.framework",
        features = "src/test/resources/features",
        tags = "@api, @Search, @Edit"
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
