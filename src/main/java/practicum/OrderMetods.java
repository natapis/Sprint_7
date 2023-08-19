package practicum;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderMetods {
    private final static String URL_CREATE_ORDER = "/api/v1/orders";
    public OrderMetods(){

    }

    public Response createOrder(Order order){
        return given()
                .header("Content-type","application/json")
                .and()
                .body(order)
                .when()
                .post(URL_CREATE_ORDER);
    }
}
