import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * меняет запись
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "srd/test/features",
        glue = "ru.dream.framework"
)

public class ChangeRecordTest {
    @BeforeClass
    public void prepare() throws IOException {
        System.setProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://misago.rnd.lanit.ru:58443"),
                .addHeader("cookie",System.setProperties("cookie"))
    }
}
