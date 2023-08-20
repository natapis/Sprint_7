package practicum.order;

import com.google.gson.Gson;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class OrderMetods {
    private final static String URL_CREATE_ORDER = "/api/v1/orders";

    public OrderMetods() {

    }

    public Response createOrder(Order order) {
        Gson gson = new Gson();
        return given()
                .header("Content-type", "text/plain")
                .and()
                .body(gson.toJson(order))
                .when()
                .post(URL_CREATE_ORDER);
    }
}
