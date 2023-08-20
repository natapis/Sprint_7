package practicum.courier;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String URL_CREATE = "/api/v1/courier";
    private static final String URL_LOGIN = "/api/v1/courier/login";
    private static final String URL_DELETE = "/api/v1/courier/{:id}";

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
