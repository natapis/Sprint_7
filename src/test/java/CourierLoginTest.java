import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import practicum.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private String id_Courier;
    private CourierClient courierClient = new CourierClient();
    private Courier courier = CourierGenerator.randomCourier();
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
        courierClient.createCourier(courier);
    }

    @Test
    public void loginCourier(){
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        loginResponse.then().assertThat().body("id", notNullValue()).and().statusCode(200);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    /*
    @After
    public void tearDown(){
    если ид курьера не пустое
        CourierClient courierClient = new CourierClient();
        Response deleteResponse = courierClient.deleteCourier(id_Courier);
        Assert.assertEquals("Курьер не удален", 200, deleteResponse.statusCode());
    }

 */
}
