package ru.yandex.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


// Вторая страница заказа
public class OrderSecondPagePom {
    private final WebDriver driver;

    // Хедер «Про аренду»
    private final By aboutRentHeader = By.xpath(".//div[text() = 'Про аренду']");
    // Поле ввода даты заказа
    private final By dateInput = By.cssSelector("[placeholder = '* Когда привезти самокат']");
    // Поле ввода срока аренды
    private final By dropdownInput = By.className("Dropdown-control");
    // Чекбокс черного цвета
    private final By blackLabel = By.cssSelector("[for = 'black']");
    // Чекбокс серого цвета
    private final By greyLabel = By.cssSelector("[for = 'grey']");
    // Поле ввода комметария для курьера
    private final By commentInput = By.cssSelector("[placeholder = 'Комментарий для курьера']");
    // Кнопка «Заказать»
    private final By orderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    // Хедер «Хотите оформить заказ?»
    private final By popUpQuestionHeader = By.xpath(".//div[text() = 'Хотите оформить заказ?']");
    // Кнопка «Да»
    private final By yesButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    // Хедер «Заказ оформлен»
    private final By orderCreatedHeader = By.xpath(".//div[text() = 'Заказ оформлен']");

    private final By firstDayInCalendar = By.xpath(".//div[@class='react-datepicker__week' and position()=1]/div[position()=1]");

    public OrderSecondPagePom(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstDayInCalendarDate() {
        new WebDriverWait(driver, 3)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                aboutRentHeader
                        ));
        driver.findElement(dateInput).click();
        driver.findElement(firstDayInCalendar).click();
    }


    public void setOrderInterval(String input) {
        driver.findElement(dropdownInput).click();
        driver.findElement(By.xpath(".//div[text() = '"+ input +"']")).click();
    }

    public void setBlackColor() {
        driver.findElement(blackLabel).click();
    }

    public void setGreyColor() {
        driver.findElement(greyLabel).click();
    }

    public void setComment(String input) {
        driver.findElement(commentInput).sendKeys(input);
    }

    private void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    private void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public void createOrder() {
        clickOrderButton();
        new WebDriverWait(driver, 3)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                popUpQuestionHeader
                        ));

        clickYesButton();
        new WebDriverWait(driver, 3)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                               orderCreatedHeader
                        ));
    }

    public boolean checkCreatedOrderVisibility() {
        return driver.findElement(orderCreatedHeader).isDisplayed();
    }
}
