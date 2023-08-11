import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePage;

import java.util.Arrays;

//Тест для выпадающего списка
@RunWith(Parameterized.class)
public class HomePageTest {

    WebDriver driver;

    private final By questionAboutImportant;

    private final By answerAboutImportant;

    private final String textInAnswerFieldShouldBe;

    private final boolean result;

    public HomePageTest(By questionAboutImportant,
                        By answerAboutImportant, String textInAnswerFieldShouldBe, boolean result) {
        this.questionAboutImportant = questionAboutImportant;
        this.answerAboutImportant = answerAboutImportant;
        this.textInAnswerFieldShouldBe = textInAnswerFieldShouldBe;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getTestData() {

        return Arrays.asList(
                new Object[][]{

                        {By.id("accordion__heading-0"), By.id("accordion__panel-0"), "Сутки — 400 рублей. Оплата курьеру " +
                                "— наличными или картой.", true},
                        {By.id("accordion__heading-1"), By.id("accordion__panel-1"), "Пока что у нас так: один заказ — один " +
                                "самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один " +
                                "за другим.", true},
                        {By.id("accordion__heading-2"), By.id("accordion__panel-2"), "Допустим, вы оформляете заказ на 8 мая. " +
                                "Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы " +
                                "оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 " +
                                "мая в 20:30.", true},
                        {By.id("accordion__heading-3"), By.id("accordion__panel-3"), "Только начиная с завтрашнего дня. Но " +
                                "скоро станем расторопнее.", true},
                        {By.id("accordion__heading-4"), By.id("accordion__panel-4"), "Пока что нет! Но если что-то срочное — " +
                                "всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                        {By.id("accordion__heading-5"), By.id("accordion__panel-5"), "Самокат приезжает к вам с полной " +
                                "зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. " +
                                "Зарядка не понадобится.", true},
                        {By.id("accordion__heading-6"), By.id("accordion__panel-6"), "Да, пока самокат не привезли. Штрафа не " +
                                "будет, объяснительной записки тоже не попросим. Все же свои.", true},
                        {By.id("accordion__heading-7"), By.id("accordion__panel-7"), "Да, обязательно. Всем самокатов! И " +
                                "Москве, и Московской области.", true},
                }
        );
    }

    @Test
    public void checkExpectedTextInAnswersEqualsActualTrue(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dalum\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver(options);
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);

        HomePage homePage = new HomePage(driver);
        homePage.wailForLoadingHomePage();
        boolean actual = homePage.checkTextInAccordionQuestionsAboutImportantFields(questionAboutImportant,
                answerAboutImportant, textInAnswerFieldShouldBe);
        Assert.assertEquals("Текст в ответах на вопросы не соответствует ожидаемому", result, actual);
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
