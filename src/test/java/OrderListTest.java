import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static practicum.constants.Api.BASE_URL;
import static practicum.constants.Api.URL_CREATE_ORDER;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Получение списка заказов без параметров")
    public void getOrderList() {
        Response getResponse = given()
                .get(URL_CREATE_ORDER);
        getResponse.then().body("orders", notNullValue()).and().statusCode(200);
    }
}
