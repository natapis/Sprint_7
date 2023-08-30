package practicum.courier;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static practicum.constants.Api.*;

public class CourierClient {

    public CourierClient() {

    }

    public Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(URL_CREATE);
    }

    public Response loginCourier(CourierCreds courierCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierCreds)
                .when()
                .post(URL_LOGIN);
    }

    public Response deleteCourier(String id) {
        return given()
                .delete(URL_DELETE, id);
    }
}
