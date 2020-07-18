package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
        features ="src\\test\\resources\\cucumber\\SearchElements.feature",
        tags = "@Feature"
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
