import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.AboutOrderPage;
import pageobject.HomePage;
import pageobject.UserInfoPage;

import java.util.Arrays;

//Основной тестовый класс
@RunWith(Parameterized.class)
public class GetOrderTest {

    private WebDriver driver;
    private String username;
    private String userSurname;
    private String address;
    private By metroStation;
    private String phoneNumber;
    private String date;
    private String rentalPeriod;
    private String comment;

    public GetOrderTest(String username, String userSurname, String address,
                        By metroStation, String phoneNumber, String date,
                        String rentalPeriod, String comment) {
        this.username = username;
        this.userSurname = userSurname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getTestData() {
        return Arrays.asList(
                new Object[][]{
                        {"Ольга", "Савельева", "г. Москва ул Николая Старостина д 10 кв 44",
                                By.xpath("//div[2]/ul/li[1]/button"), "89162348765", "27.07.2023",
                        "сутки", "Желательно привезти до 10 часов утра"},
                        {"Ольга", "Савельева", "г. Москва ул Николая Старостина д 10 кв 44",
                                By.xpath(".//div[2]/ul/li[4]"), "000", "27.07.2023",
                                "сутки", "Желательно привезти до 10 часов утра"},
                        {"Olga", "Severova", "address rec. 123",
                                By.xpath(".//div[2]/ul/li[5]"), "89162348765", "27.07.2023",
                                "сутки", "Желательно привезти до 10 часов утра"},
                        {"вылдваолв", "рвыраылоров", "арфоралоыраолыа",
                                By.xpath(".//div[2]/ul/li[8]"), "фырлыфоврлоы", "27.07.2023",
                                "сутки", "Желательно привезти до 10 часов утра"},

                }
        );
    }

    @Before
    public void startPageInitialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dalum\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver(options);
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);
    }


    public void insertInfoInPages(){
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        UserInfoPage orderPage = new UserInfoPage(driver);
        orderPage.wailForLoadingOrderPage();
        orderPage.clickOnAcceptCookieButton();
        orderPage.fillFieldsInPage(username, userSurname, address, phoneNumber, metroStation);
        orderPage.clickOnFarther();
        AboutOrderPage aboutOrderPage = new AboutOrderPage(driver);
        aboutOrderPage.waitForDownloadingPage();
        aboutOrderPage.fillAboutOrderPage(date, rentalPeriod, comment);
        aboutOrderPage.clickOnOrderButton();
        aboutOrderPage.clickOnConfirmOrderButton();
    }

    @Test
    public void getOrderByHeaderButton() {

        startPageInitialize();
        HomePage homePage = new HomePage(driver);
        homePage.wailForLoadingHomePage();
        homePage.clickOnOrderButtonInHeader();

        insertInfoInPages();
    }

    @Test
    public void getOrderByMiddleButton() {

        HomePage homePage = new HomePage(driver);
        homePage.wailForLoadingHomePage();
        homePage.clickOnOrderButtonInMiddle();

        insertInfoInPages();
    }

    @After
    public void teardown(){
        driver.quit();
    }

}
