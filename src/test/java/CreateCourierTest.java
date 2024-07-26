import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.courier.*;


public class CreateCourierTest {

    private String courierId;
    private CreateCourier createCourier;
    private final CourierSteps courierSteps = new CourierSteps();
    private final CourierPull pulls = new CourierPull();

    @Test
    @DisplayName("Создание курьера")
    @Description("Позитивная проверка")
    public void createCourier() {
        createCourier = CreateCourier.creater();
        ValidatableResponse responseCreate  = courierSteps.createCourier(createCourier);
        pulls.checkCreateOk(responseCreate );

        LoginCourier loginCourier = LoginCourier.from(createCourier);
        ValidatableResponse loginResponse = courierSteps.loginCourier(loginCourier);
        courierId = String.valueOf(pulls.checkLoginOk(loginResponse));
    }


    @Test
    @DisplayName("Запрос без пароля")
    @Description("Негативная проверка")
    public void createCourierWithoutPassword() {
        createCourier = CreateCourier.creater();
        CreateCourier courierWithoutPassword = CreateCourier.from(createCourier);
        courierWithoutPassword.setPassword(null);

        ValidatableResponse responseCreate = courierSteps.createCourier(courierWithoutPassword);
        pulls.checkCreateWithoutMandatoryData(responseCreate );

    }

    @Test
    @DisplayName("Запрос с повторяющимся логином")
    @Description("Негативная проверка")
    public void createCourierDuplicate() {
        createCourier = new CreateCourier("naruto", "1234", "saske");
        courierSteps.createCourier(createCourier);

        CreateCourier courierDuplicate = CreateCourier.from(createCourier);
        ValidatableResponse responseCreate  = courierSteps.createCourier(courierDuplicate);
        pulls.checkCreateWithDuplicate(responseCreate );
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
