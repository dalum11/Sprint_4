package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Страница информации о пользователе
public class UserInfoPage {

    //Драйвер браузера
    private WebDriver driver;

    //Кнопка принятия куки
    private static final By ACCEPT_COOKIE_BUTTON = By.id("rcc-confirm-button");

    //Имя пользователя
    private static final By USER_NAME = By.xpath(".//div[2]/div[1]/input");

    //Фамилия пользователя
    private static final By USER_SURNAME = By.xpath(".//div[2]/div[2]/input");

    //Адрес пользователя
    private static final By ADDRESS = By.xpath(".//div[2]/div[3]/input");

    //Возле какой станции метро живёт пользователь
    private static final By METRO_STATION = By.xpath(".//div[4]/div/div/input");

    //Номер телефона пользователя
    private static final By PHONE_NUMBER = By.xpath(".//div[5]/input");

    //Кнопка Далее
    private static final By BUTTON_FARTHER = By.cssSelector("div.Order_NextButton__1_rCA > button");

    public UserInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    //Принимает куки по кнопке
    public void clickOnAcceptCookieButton(){
        WebElement acceptCookieButton = null;
        try {
            acceptCookieButton = driver.findElement(ACCEPT_COOKIE_BUTTON);
        } catch (NoSuchElementException e) {
            System.out.println("Кнопка не найдена");
        }
        if (acceptCookieButton != null) {
            acceptCookieButton.click();
        }
    }

    //Ждёт загрузку страницы
    public void wailForLoadingOrderPage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOf(driver.findElement(USER_NAME)));
    }

    //Заполняет имя пользователя
    public void fillUserName(String username) {
        driver.findElement(USER_NAME).sendKeys(username);
    }

    //Заполняет фамилию
    public void fillUserSurname(String surname) {
        driver.findElement(USER_SURNAME).sendKeys(surname);
    }

    //Заполняет адрес
    public void fillAddress(String addr) {
        driver.findElement(ADDRESS).sendKeys(addr);
    }

    //Заполняет телефон
    public void fillPhone(String number){
        driver.findElement(PHONE_NUMBER).sendKeys(number);
    }

    //Заполняет станцию метро
    public boolean fillMetroStation(By station) {
        driver.findElement(METRO_STATION).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(
                        By.cssSelector("div.select-search__select > ul > li:nth-child(1)"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(station));
        driver.findElement(station).click();
        return true;
    }

    //Заполняет все поля на странице
    public void fillFieldsInPage(String username, String surname, String addr, String number, By station) {
        fillUserName(username);
        fillUserSurname(surname);
        fillAddress(addr);
        fillMetroStation(station);
        fillPhone(number);
    }

    //Нажимает на кнопку Далее
    public void clickOnFarther(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(BUTTON_FARTHER));
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(driver.findElement(BUTTON_FARTHER)));
        driver.findElement(BUTTON_FARTHER).click();
    }

}
