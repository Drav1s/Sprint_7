package ru.yandex.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.CONSTANT.*;


public class CourierSteps {

    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(HOST);
    }


    @Step("Создание курьера")
    public ValidatableResponse createCourier(CreateCourier createCourier) {
        return requestSpec()
                .body(createCourier)
                .when()
                .post(CREATE)
                .then().log().all();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse loginCourier(LoginCourier loginCourier) {
        return requestSpec()
                .body(loginCourier)
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(CourierRemove courierRemove) {
        return requestSpec()
                .body(courierRemove)
                .when()
                .delete(REMOVE + courierRemove.getCourierId())
                .then().log().all();
    }
}
