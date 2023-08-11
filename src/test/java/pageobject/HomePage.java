package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Домашняя страница
public class HomePage {

    // Драйвер браузера
    private WebDriver driver;

    // Список вопросов о важном
    private static final By ACCORDION_QUESTIONS_ABOUT_IMPORTANT = By.className("accordion__button");

    // Кнопка заказа вверху экрана
    private static final By GET_ORDER_IN_HEADER = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g");

    // Кнопка заказа в середине экрана
    private static final By GET_ORDER_IN_MIDDLE = By.cssSelector("div.Home_FinishButton__1_cWm > button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажимает на кнопку заказа вверху страницы
    public void clickOnOrderButtonInHeader() {
        driver.findElement(GET_ORDER_IN_HEADER).click();
    }

    //Нажимает на кнопку заказа посередине страницы
    public void clickOnOrderButtonInMiddle() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(GET_ORDER_IN_MIDDLE));
        driver.findElement(GET_ORDER_IN_MIDDLE).click();
    }

    //Ждёт загрузку страницы
    public void wailForLoadingHomePage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfAllElements(driver.findElements(ACCORDION_QUESTIONS_ABOUT_IMPORTANT)));
    }

    // Проверяет текст списка о важном на соответствие
    public boolean checkTextInAccordionQuestionsAboutImportantFields(By question, By answer, String text) {
        WebElement questionAboutImportant = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionAboutImportant);

        questionAboutImportant.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionAboutImportant);
        WebElement answerAboutImportant = driver.findElement(answer);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(answerAboutImportant));
        return text.equals(answerAboutImportant.getText());

    }

}
