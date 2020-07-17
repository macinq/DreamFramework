package ru.dream.framework.citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/** Класс с REST API тестами.
 * @author Alexander Karpyuk.
 */
@Test
public class RestIT extends TestNGCitrusTestDesigner {
    @Autowired
    private HttpClient client;


    /**
     * GET запрос, ожидаемый результат: Status Code = 200.
     */
    @CitrusTest
    public void testPositiveRequest() {
        http().client(client)
                .send()
                .get();

        http().client(client)
                .receive()
                .response(HttpStatus.OK);
    }
}
