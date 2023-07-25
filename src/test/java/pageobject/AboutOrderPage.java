package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutOrderPage {

    //Драйвер браузера
    private WebDriver driver;

    //Дата, когда привезут самокат
    private static final By DATE_INPUT_FIELD = By.xpath("//input[@placeholder ='* Когда привезти самокат']");

    //Выпадающий список срока аренды
    private static final By RENTAL_PERIOD_MENU = By.xpath("//span[@class = 'Dropdown-arrow']");

    //Комментарий курьеру
    private static final By COMMENT_FOR_COURIER = By.xpath(".//div/div[2]/div[2]/div[4]/input");

    //Кнопка Заказать
    private static final By ORDER_BUTTON = By.cssSelector("div.Order_Buttons__1xGrp > button:nth-child(2)");

    //Кнопка подтверждения заказа
    private static final By CONFIRM_ORDER = By.xpath("//div[5]/div[2]/button[2]");

    public AboutOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполняет комментарий курьеру
    public void fillCommentForCourier(String comment) {
        driver.findElement(COMMENT_FOR_COURIER).sendKeys(comment);
    }

    //Нажимает на кнопку Заказать
    public void clickOnOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    //Ждёт загрузки всплывающего окна
    public void waitForLoadingPopUp() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfAllElements(driver.findElements(CONFIRM_ORDER)));
    }

    //Ждёт загрузки страницы
    public void waitForDownloadingPage() {
        new WebDriverWait(driver, 10);
    }

    //Нажимает на кнопку подтверждения заказа
    public void clickOnConfirmOrderButton() {
        waitForLoadingPopUp();
        driver.findElement(CONFIRM_ORDER).click();
    }

    //Заполняет дату
    public void fillDate(String date) {
        driver.findElement(DATE_INPUT_FIELD).click();
        driver.findElement(DATE_INPUT_FIELD).sendKeys(date);
    }

    //Заполняет период аренды
    public void fillRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_PERIOD_MENU).click();
        By rentalPeriodValue = By.xpath(".//*[contains(text(), '" + rentalPeriod + "') and starts-with(@class, 'Dropdown')]");
        driver.findElement(rentalPeriodValue).click();
    }

    //Заполняет страницу заказа
    public void fillAboutOrderPage(String date, String rentalPeriod, String comment) {
        fillDate(date);
        fillRentalPeriod(rentalPeriod);
        fillCommentForCourier(comment);
    }
}
