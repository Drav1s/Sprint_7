import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.courier.*;

public class LoginCourierTest {

    private String courierId;
    private CreateCourier createCourier;
    private final CourierSteps courierSteps = new CourierSteps();
    private final CourierPull pulls = new CourierPull();

    @Before
    @DisplayName("Создание курьера для дальнейших проверок")
    public void createCourier() {
        createCourier = CreateCourier.creater();
        courierSteps.createCourier(createCourier);
    }

    @Test
    @DisplayName("login courier")
    @Description("Позитивная проверка")
    public void loginCourier() {
        LoginCourier loginCourier = LoginCourier.from(createCourier);

        ValidatableResponse loginResponse = courierSteps.loginCourier(loginCourier);
        courierId = String.valueOf(pulls.checkLoginOk(loginResponse));
    }

    @Test
    @DisplayName("Запрос c несуществующей парой логин-пароль")
    @Description("Негативная проверка")
    public void loginWithNonExistentData() {
        LoginCourier loginCourier = LoginCourier.from(createCourier);
        loginCourier.setLogin("Potato");

        ValidatableResponse loginResponse = courierSteps.loginCourier(loginCourier);
        pulls.checkLoginWithNonExistentData(loginResponse);
    }

    @Test
    @DisplayName("Запрос без логина или пароля")
    @Description("Негативная проверка")
    public void loginWithoutMandatoryData() {
        LoginCourier loginCourier = LoginCourier.from(createCourier);
        loginCourier.setLogin(null);

        ValidatableResponse loginResponse = courierSteps.loginCourier(loginCourier);
        pulls.checkLoginWithoutMandatoryData(loginResponse);
    }

    @After
    @DisplayName("Удаления курьер")
    @Description("Очистка данных")
    public void deleteCourier() {
        if (courierId != null) {
            CourierRemove courierRemove = new CourierRemove(courierId);

            ValidatableResponse removalResponse = courierSteps.deleteCourier(courierRemove);
            pulls.checkRemoveOk(removalResponse);
        }
    }
}
