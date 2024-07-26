package ru.yandex.praktikum.orders;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.CONSTANT.*;

public class OrdersSteps {

    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(HOST);
    }

    @Step("Создание заказа")
    public ValidatableResponse createNewOrder(ListOfOrders listOfOrders) {
        return requestSpec()
                .body(listOfOrders)
                .when()
                .post(CREATE_GET_ORDER)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return requestSpec()
                .when()
                .get(CREATE_GET_ORDER)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(int track) {
        return requestSpec()
                .body(track)
                .when()
                .put(CANCEL)
                .then();
    }
}
