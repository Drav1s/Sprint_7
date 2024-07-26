import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.praktikum.orders.*;

import static org.hamcrest.CoreMatchers.notNullValue;

public class ListOfOrdersTest {

    OrdersSteps ordersSteps = new OrdersSteps();

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Позитивная проверка")
    public void getOrderList() {
        ValidatableResponse responseOrderList = ordersSteps.getOrderList();
        responseOrderList.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
