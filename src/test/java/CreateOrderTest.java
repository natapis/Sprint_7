import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.order.Order;
import practicum.order.OrderGenerate;
import practicum.order.OrderMetods;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    private final String[] color;
    private final int expected;

    public CreateOrderTest(String[] color, int expected) {
        this.color = color;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Parameterized.Parameters(name = "Создание заказа на самокат цвета {0} - {1}")
    public static Object[][] getColor() {
        return new Object[][]{
                {new String[]{"BLACK"}, 201},
                {new String[]{"BLACK", "GRAY"}, 201},
                {new String[]{}, 201}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrder() {
        OrderMetods orderMetods = new OrderMetods();
        Order order = OrderGenerate.generateOrder(color);
        Response createResponse = orderMetods.createOrder(order);
        createResponse.then().assertThat().body("track", notNullValue()).and().statusCode(201);
        int actual = createResponse.statusCode();
        Assert.assertEquals("Не совпадают коды", expected, actual);
    }

}
