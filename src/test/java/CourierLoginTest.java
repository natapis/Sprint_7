import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practicum.courier.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static practicum.RandomString.randomString;
import static practicum.constants.Api.BASE_URL;

public class CourierLoginTest {
    private String idCourier;
    private CourierClient courierClient = new CourierClient();

    private Courier courier = CourierGenerator.randomCourier();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient.createCourier(courier);
        idCourier = "";
    }

    @Test
    @DisplayName("Авторизация курьера")
    public void loginCourier() {
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        loginResponse.then().assertThat().body("id", notNullValue()).and().statusCode(200);
    }

    @Test
    @DisplayName("Авторизация с неправильным паролем")
    public void loginCourierWrongPassword() {
        String login = courier.getLogin();
        String passwordWrong = courier.getPassword() + 't';
        CourierCreds creds = new CourierCreds(login, passwordWrong);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void loginCourierWithoutPassword() {
        String login = courier.getLogin();
        CourierCreds creds = new CourierCreds(login, null);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Test
    @DisplayName("Авторизация без логина")
    public void loginCourierWithoutLogin() {
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(null, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Test
    @DisplayName("Авторизация с неправильным логином")
    public void loginCourierWrongLogin() {
        String loginWrong = courier.getLogin() + 't';
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(loginWrong, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Авторизация с несуществующим логином")
    public void loginCourierNotExist() {
        String loginNotExist = randomString(8);
        String password = courier.getPassword();
        CourierCreds creds = new CourierCreds(loginNotExist, password);
        Response loginResponse = courierClient.loginCourier(creds);
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }


    @After
    public void tearDown() {
        Response loginResponseOld = courierClient.loginCourier(CourierCreds.credsForm(courier));
        if (loginResponseOld.statusCode() == 200) {
            idCourier = loginResponseOld.body().as(LoginAnswer.class).getId();
            Response deleteResponse = courierClient.deleteCourier(idCourier);
            Assert.assertEquals("Курьер не удален", 200, deleteResponse.statusCode());
        }
    }

}
