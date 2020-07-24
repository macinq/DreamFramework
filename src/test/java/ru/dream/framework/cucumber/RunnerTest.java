package ru.dream.framework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import ru.dream.framework.cucumber.steps.LoginProfileSteps;

@CucumberOptions(
        glue = "ru.dream.framework",
        features = "src/test/resources/features",
        tags = "@api"
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
