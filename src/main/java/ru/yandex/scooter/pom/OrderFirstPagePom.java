package ru.yandex.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// Первая страница заказа
public class OrderFirstPagePom {

    private final WebDriver driver;

    // Хедер «Для кого самокат»
    private final By forWhomHeader = By.xpath(".//div[text() = 'Для кого самокат']");
    // Поле ввода имени
    private final By nameInput = By.cssSelector("[placeholder = '* Имя']");
    // Поле ввода фамилии
    private final By surnameInput = By.cssSelector("[placeholder = '* Фамилия']");
    // Поле ввода адреса
    private final By addressInput = By.cssSelector("[placeholder = '* Адрес: куда привезти заказ']");
    // Поле ввода телефона
    private final By phoneInput = By.cssSelector("[placeholder = '* Телефон: на него позвонит курьер']");
    // Поле воода станции метро
    private final By subwayInput = By.className("select-search__input");
    // Кнопка «Далее»
    private final By nextButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']");
    // Кнопка закрытия всплывающего окна куков
    private final By cookieButton = By.xpath(".//button[@class = 'App_CookieButton__3cvqF']");

    private void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    private boolean findCookieButton() {
        return driver.findElement(cookieButton).isDisplayed();
    }

    public OrderFirstPagePom(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String input) {
        new WebDriverWait(driver, 3)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                forWhomHeader
                        ));
        if (findCookieButton()) {
            clickCookieButton();
        }
        driver.findElement(nameInput).sendKeys(input);
    }

    public void setSurname(String input) {
        driver.findElement(surnameInput).sendKeys(input);
    }

    public void setAddress(String input) {
        driver.findElement(addressInput).sendKeys(input);
    }

    public void setPhoneInput(String input) {
        driver.findElement(phoneInput).sendKeys(input);
    }

    public void setSubway(String input) {
        driver.findElement(subwayInput).click();
        driver.findElement(By.xpath(".//div[text() = '"+ input +"']")).click();
    }

    private void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public OrderSecondPagePom startNextProcess() {
        clickNextButton();
        return new OrderSecondPagePom(driver);
    }


}
