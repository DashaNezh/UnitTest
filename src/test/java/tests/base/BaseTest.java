package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.base.BasePage;
import pages.homepage.HomePage;

import static common.Config.CLEAR_COOKIES_AND_STORAGES;
import static common.Config.HOLD_BROWSER_OPEN;
import static common.Config.HOMEPAGE;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;

    @AfterMethod(alwaysRun = true)
    public void clearCookiesAndLocalStorages(){
        if(CLEAR_COOKIES_AND_STORAGES){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }
    @BeforeMethod(alwaysRun = true)
    public void openMainPage () {
        driver = CommonActions.createDriver();
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);

        basePage.openPage(HOMEPAGE);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (HOLD_BROWSER_OPEN) {
            driver.quit();
        }
    }
}
