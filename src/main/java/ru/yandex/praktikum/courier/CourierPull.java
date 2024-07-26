package ru.yandex.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierPull {

    @Step("Успешное создание учётной записи")
    public void checkCreateOk(ValidatableResponse response) {
        response
                .statusCode(201)
                .assertThat().body("ok", Matchers.equalTo(true));
    }

    @Step("Запрос без логина или пароля")
    public void checkCreateWithoutMandatoryData(ValidatableResponse response) {
        response
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Запрос с повторяющимся логином")
    public void checkCreateWithDuplicate(ValidatableResponse response) {
        response
                .statusCode(409)
                .assertThat().body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Успешный логин")
    public Integer checkLoginOk(ValidatableResponse response) {
        return response
                .statusCode(200)
                .assertThat().body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Step("Запрос без логина или пароля")
    public void checkLoginWithoutMandatoryData (ValidatableResponse response) {
        response
                .statusCode(400)
                .assertThat().body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Step("Запрос c несуществующей парой логин-пароль")
    public void checkLoginWithNonExistentData(ValidatableResponse response) {
        response
                .statusCode(404)
                .assertThat().body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @Step("Успешное удаление")
    public void checkRemoveOk(ValidatableResponse response) {
        response
                .statusCode(200)
                .assertThat().body("ok", Matchers.equalTo(true));
    }
}
