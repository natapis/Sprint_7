import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private final String URL_ORDER_LIST = "/api/v1/orders";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Получение списка заказов без параметров")
    public void getOrderList() {
        Response getResponse = given()
                .get(URL_ORDER_LIST);
        getResponse.then().body("orders", notNullValue()).and().statusCode(200);
    }
}
