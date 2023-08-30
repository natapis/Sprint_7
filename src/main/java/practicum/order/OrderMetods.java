package practicum.order;

import com.google.gson.Gson;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static practicum.constants.Api.URL_CREATE_ORDER;

public class OrderMetods {

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
