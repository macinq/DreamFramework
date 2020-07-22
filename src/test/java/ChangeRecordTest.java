import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 * меняет запись
 */


public class ChangeRecordTest {
    @BeforeClass
    public static void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("src\\test\\java\\my.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://misago.rnd.lanit.ru:58443/api/threads/")
                .addHeader("Cookie", System.getProperty("cookie"))
                .addHeader("X-CSRFToken", System.getProperty("Token"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void tryChange() {
        ChangeTitle changeTitle = new ChangeTitle();
        changeTitle.setOp("replace");
        changeTitle.setPath("title");
        changeTitle.setValue("Проверка");
        given()
                .body(changeTitle)
                .when()
                .patch("/3")
                .then()
                .statusCode(200);
    }
}
