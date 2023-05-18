import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import ru.yandex.scooter.pom.MainPagePom;
import static org.junit.Assert.*;

import java.util.List;


@RunWith(Parameterized.class)
public class ScooterAccordionTest extends ScooterBaseTest {

    private final int accordionItemNumber;

    private final String expectedAccordionHiddenPanelText;

    public ScooterAccordionTest(int accordionItemNumber, String expectedAccordionHiddenPanelText) {
        this.accordionItemNumber = accordionItemNumber;
        this.expectedAccordionHiddenPanelText = expectedAccordionHiddenPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void checkAccordionItemsHiddenText() {

        MainPagePom mainPagePom = new MainPagePom(driver);

        List<WebElement> accordionList = mainPagePom.getAccordionItems();
        assertTrue("Номер тестового вопроса больше чем количество вопросов в списке", accordionList.size() - 1 >= accordionItemNumber);
        assertTrue("Элемент списка не открывается или открыт изначально", mainPagePom.checkAccordionElementTextOpening(accordionList.get(accordionItemNumber)));
        assertEquals("Не совпадает текст элемента", expectedAccordionHiddenPanelText, mainPagePom.getAccordionText(accordionList.get(accordionItemNumber)));
    }
}
