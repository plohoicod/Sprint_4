import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.scooter.pom.MainPagePom;
import ru.yandex.scooter.pom.OrderFirstPagePom;
import ru.yandex.scooter.pom.OrderSecondPagePom;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends ScooterBaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phone;
    private final String interval;
    private final String comment;


    public ScooterOrderTest( String name,
     String surname,
     String address,
     String subway,
     String phone,
     String interval,
     String comment) {
        this.name = name;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.interval = interval;
        this.comment = comment;
        this.surname = surname;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Имя", "Фамилия", "Улица Дом", "Бульвар Рокоссовского", "12345678900", "сутки", "привет"},
                {"НеИмя", "НеФамилия", "Не Улица Дом", "Черкизовская", "00987654321", "двое суток", "пока"}
        };
    }

    @Test
    public void checkUpOrder() {
        MainPagePom mainPagePom = new MainPagePom(driver);
        assertTrue("Верхняя кнопка заказа отсуствует", mainPagePom.findUpOrderButton());
        assertOrderResults(processOrder(mainPagePom.startUpOrderProcess()));

    }

    @Test
    public void checkDownOrder() {
        MainPagePom mainPagePom = new MainPagePom(driver);
        assertTrue("Нижняя кнопка заказа отсуствует", mainPagePom.findDownOrderButton());
        assertOrderResults(processOrder(mainPagePom.startDownOrderProcess()));
    }

    private void assertOrderResults(OrderSecondPagePom orderSecondPagePom) {
        assertNotNull("Ошибка создания заказа", orderSecondPagePom);
        assertTrue("Всплывающее окно не появилось", orderSecondPagePom.checkCreatedOrderVisibility());
    }

    private OrderSecondPagePom processOrder(OrderFirstPagePom orderFirstPagePom) {

        try {
            orderFirstPagePom.setName(name);
            orderFirstPagePom.setSurname(surname);
            orderFirstPagePom.setAddress(address);
            orderFirstPagePom.setSubway(subway);
            orderFirstPagePom.setPhoneInput(phone);

            OrderSecondPagePom orderSecondPagePom = orderFirstPagePom.startNextProcess();
            orderSecondPagePom.setFirstDayInCalendarDate();
            orderSecondPagePom.setOrderInterval(interval);
            orderSecondPagePom.setBlackColor();
            orderSecondPagePom.setGreyColor();
            orderSecondPagePom.setComment(comment);
            orderSecondPagePom.createOrder();
            return orderSecondPagePom;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
