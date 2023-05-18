package ru.yandex.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
//Главная страница сайта
public class MainPagePom {
    private final WebDriver driver;
    // Элемент выпадающего списка
    private final By accordionItem = By.className("accordion__item");
    // Скрытый текст выпадающего списка
    private final By accordionPanel = By.className("accordion__panel");
    // Кнопка открытия скрытого текста выпадающего текста
    private final By accordionButton = By.className("accordion__button");
    // Кнопка «Заказать» верхняя
    private final By upOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    // Кнопка «Заказать» нижняя
    private final By downOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    public MainPagePom(WebDriver driver) {
        this.driver = driver;
    }

    private void clickAccordionButton(WebElement webElement) {
        webElement.findElement(accordionButton).click();
    }

    public List<WebElement> getAccordionItems() {
        return driver.findElements(accordionItem);
    }

    public String getAccordionText(WebElement webElement) {
        return webElement.findElement(accordionPanel).getText();
    }

    private boolean checkAccordionTextPanelVisibility(WebElement webElement) {
        return webElement.findElement(accordionPanel).isDisplayed();
    }

    private void waitForTextPanelVisibility(WebElement webElement){

        new WebDriverWait(driver, 3)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.id(
                                        webElement.findElement(accordionPanel).getAttribute("id")
                                )));
    }


    public boolean checkAccordionElementTextOpening(WebElement webElement) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
        if (checkAccordionTextPanelVisibility(webElement)) {
            return false;
        }
        clickAccordionButton(webElement);
        waitForTextPanelVisibility(webElement);
        if (!checkAccordionTextPanelVisibility(webElement)) {
            return false;
        }
        return true;
    }



    public boolean findUpOrderButton() {
        return driver.findElement(upOrderButton).isDisplayed();
    }

    public boolean findDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return driver.findElement(downOrderButton).isDisplayed();
    }

    public void clickDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        driver.findElement(downOrderButton).click();
    }

    private void clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
    }

    public OrderFirstPagePom startUpOrderProcess() {

        clickUpOrderButton();

        return new OrderFirstPagePom(driver);
    }

    public OrderFirstPagePom startDownOrderProcess() {

        clickDownOrderButton();

        return new OrderFirstPagePom(driver);
    }
}
