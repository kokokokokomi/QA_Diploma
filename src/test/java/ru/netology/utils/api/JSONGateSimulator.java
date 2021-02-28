package ru.netology.utils.api;

import ru.netology.data.CardJSON;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static ru.netology.utils.api.AuthTest.requestSpec;

public class JSONGateSimulator {

    private JSONGateSimulator() {
    }

    public static String jsonByCredit(CardJSON card) {
        Response response =
                given()
                        .spec(requestSpec)
                        .body(card)
                        .when()
                        .post("/credit")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String status = response.path("status");
        return status;
    }

    public static String jsonByPayment(CardJSON card) {
        Response response =
                given()
                        .spec(requestSpec)
                        .body(card)
                        .when()
                        .post("/payment")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String status = response.path("status");
        return status;
    }

    public static void jsonByPaymentInvalidCard(CardJSON card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/payment")
                .then()
                .statusCode(400);
    }

    public static void jsonByCreditInvalidCard(CardJSON card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/credit")
                .then()
                .statusCode(400);
    }
}
