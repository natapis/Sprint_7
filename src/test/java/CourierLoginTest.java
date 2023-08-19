import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import practicum.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static practicum.RandomString.randomString;

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

    @Test
    public void loginCourierWrongPassword(){
        String login = courier.getLogin();
        String passwordWrong = courier.getPassword() + 't';
        CourierCreds creds = new CourierCreds(login, passwordWrong);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    @Test
    public void loginCourierWithoutPassword(){
        String login = courier.getLogin();
        CourierCreds creds = new CourierCreds(login, null);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }
    @Test
    public void loginCourierWithoutLogin(){
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(null, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();

    }

    @Test
    public void loginCourierWrongLogin(){
        String loginWrong = courier.getLogin()+'t';
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(loginWrong, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    @Test
    public void loginCourierNotExist(){
        String loginNotExist = randomString(8);
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(loginNotExist, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
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
