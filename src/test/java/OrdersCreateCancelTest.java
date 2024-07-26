import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.orders.ListOfOrders;
import ru.yandex.praktikum.orders.OrdersSteps;
import org.junit.After;



import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrdersCreateCancelTest {

    private final String[] colour;
    private int track;
    private final OrdersSteps ordersSteps = new OrdersSteps();

    public OrdersCreateCancelTest(String[] colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {new String[] {"BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {"BLACK", "GREY"}},
                {new String[] {}},
        };
    }


    @Test
    @DisplayName("Создание заказа с самокатами разных цветов")
    @Description("Проверка, что когда создаёшь заказ:\n" +
            "можно указать один из цветов — BLACK или GREY;\n" +
            "можно указать оба цвета;\n" +
            "можно совсем не указывать цвет")
    public void CreateOrderWithDiffColours() {
        ListOfOrders listOfOrders = ListOfOrders.createOrder(colour);
        ValidatableResponse responseCreateOrder = ordersSteps.createNewOrder(listOfOrders);
        track = responseCreateOrder.extract().path("track");
        responseCreateOrder.assertThat()
                .body("track", notNullValue())
                .extract()
                .path("track");
    }

    @After
    @Step("Отмена заказа")
    public void CancelTestOrder() {
        ordersSteps.cancelOrder(track);
    }
}
