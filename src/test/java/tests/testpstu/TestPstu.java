package tests.testpstu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.homepage.HomePage;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.Config.HOMEPAGE;
import static constants.Constants.TimeOutVariable.IMPLICIT_WAIT;
import static constants.Constants.Urls.ERROR_LOGIN;


public class TestPstu extends BaseTest {
    private final String username = "nezhdanovad10";
    private final String password = "tiMeisup2023@";

    @Test(groups = "positive")
    void testLoginPositive(){
        String actualResult;
        String expectedResult = "https://do.pstu.ru/";

        homePage.login(username, password);

        actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test (expectedExceptions= {HomePage.ErrorLogin.class}, groups = "negative")
    void testLoginNegative() throws Exception{
        String usernameError = "nezhdanovad10";
        String passwordError = "tiMeisup2023";

        homePage.login(usernameError, passwordError);
        homePage.errorOfLogin(ERROR_LOGIN);
    }
    @Test (groups = "positive")
    void testFirstCoursePositive() throws Exception{
        homePage.login(username, password);
        String expectedResult = "https://do.pstu.ru/course/view.php?id=363";
        String actualResult;

        homePage.firstCourse();

        actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test (expectedExceptions= {HomePage.ErrorPage.class}, groups = "negative")
    void testFirstCourseNegative() throws Exception{
        homePage.firstCourse();
    }
    @Test (groups = "positive")
    void testGetMainMenu(){
        homePage.login(username, password);
        List<WebElement> actualElements = homePage.getMainMemu();
        List<String> actualResult = new ArrayList<>();
        List<String> expectedResult = Arrays.asList(
                "О пользователе", "Оценки", "Календарь", "Сообщения",
                "Личные файлы", "Отчеты", "Настройки", "Язык", "Выход"
        );

        // Преобразование веб-элементов в строки
        for (WebElement element : actualElements) {
            actualResult.add(element.getText());
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test (groups = "positive")
    void testExitPositive() throws Exception{
        String actualResult;
        String expectedResult = "Logging out";
        homePage.login(username, password);

        homePage.exit();

        actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(expectedExceptions = {HomePage.ErrorExit.class}, groups = "negative")
    void testExitNegative() throws Exception {
        homePage.exit();
    }
    @Test (expectedExceptions = {HomePage.ErrorPage.class}, groups = "negative")
    void testPersonalAccountNegative() throws Exception{
        homePage.personalAccount();
    }
}