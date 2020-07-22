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
    /*
    номер поста
     */
    int post = 3;
    @BeforeClass
    public static void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(System.getProperty("Site"))
                .addCookie(System.getProperty("Cookie"))
                .addHeader("X-CSRFToken", System.getProperty("TOKEN"))
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
                .patch("/" + post)
                .then()
                .statusCode(200);
    }
}
