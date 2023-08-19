import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private final String[] color;
    private final int expected;

    public CreateOrderTest(String[] color, int expected){
        this.color = color;
        this.expected = expected;
    }
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }
    @Parameterized.Parameters
    public static Object[][] getColor(){
        return new Object[][]{
                {"BLACK",201},
        {"BLACK", "GRAY",201},
        {null, 201}
        };
    }

    @Test
    public void createOrder(){
        Order order = OrderGenerate.generateOrder(color);
        OrderMetods orderMetods = new OrderMetods();
        Response createResponse = orderMetods.createOrder(order);
        createResponse.then().assertThat().body("track", notNullValue()).and().statusCode(201);
        int actual = createResponse.statusCode();
        Assert.assertEquals(expected, actual);
    }

}
