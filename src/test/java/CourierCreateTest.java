import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import practicum.courier.*;


import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateTest {
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private String id_Courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        id_Courier = "";
    }

    @Test
    @DisplayName("Создание курьера со всеми параметрами")
    public void createFullDateCourierTest() {
        CourierClient courierClient = new CourierClient();
        Courier courier = CourierGenerator.randomCourier();
        Response createResponse = courierClient.createCourier(courier);
        Assert.assertEquals("Неверный статус ответа", 201, createResponse.statusCode());
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        Assert.assertEquals("Логин не осуществляется", 200, loginResponse.statusCode());
        createResponse.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    @Test
    @DisplayName("Создание курьера с уже существующим логином")
    public void createDoubleCourier() {
        CourierClient courierClient = new CourierClient();
        Courier courier = CourierGenerator.randomCourier();
        String login_courier_one = courier.getLogin();
        courierClient.createCourier(courier);
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        Assert.assertEquals("Логин не осуществляется", 200, loginResponse.statusCode());
        Courier courier_two = CourierGenerator.doubleCourier(login_courier_one);
        Response createResponse = courierClient.createCourier(courier_two);
        createResponse.then().assertThat().body("message", equalTo("Этот логин уже используется")).and().statusCode(409);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    @Test
    @DisplayName("Создание курьера только с обязательными полями")
    public void createCourierWithRequiredFields() {
        CourierClient courierClient = new CourierClient();
        Courier courier = CourierGenerator.requiredFields();
        Response createResponse = courierClient.createCourier(courier);
        Assert.assertEquals("Неверный статус ответа", 201, createResponse.statusCode());
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        Assert.assertEquals("Логин не осуществляется", 200, loginResponse.statusCode());
        createResponse.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
        id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        CourierClient courierClient = new CourierClient();
        Courier courier = CourierGenerator.withoutPassword();
        Response createResponse = courierClient.createCourier(courier);
        Assert.assertEquals("Неверный статус ответа для курьера без пароля", 400, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        Assert.assertEquals("Не совпадают коды статусов", 400, loginResponse.statusCode());
        if (loginResponse.statusCode() == 200) {
            id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
        }
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void creteCourierWithoutLogin() {
        CourierClient courierClient = new CourierClient();
        Courier courier = CourierGenerator.withoutLogin();
        Response createResponse = courierClient.createCourier(courier);
        Assert.assertEquals("Неверный статус ответа для курьера без логина", 400, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
        Response loginResponse = courierClient.loginCourier(CourierCreds.credsForm(courier));
        Assert.assertEquals("Не совпадают коды статусов", 400, loginResponse.statusCode());
        if (loginResponse.statusCode() == 200) {
            id_Courier = loginResponse.body().as(LoginAnswer.class).getId();
        }
    }

    @After
    public void tearDown() {
        if (!(id_Courier.isEmpty())) {
            CourierClient courierClient = new CourierClient();
            Response deleteResponse = courierClient.deleteCourier(id_Courier);
            Assert.assertEquals("Курьер не удален", 200, deleteResponse.statusCode());
        }
    }


}
