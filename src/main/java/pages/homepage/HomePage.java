package pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;

import static constants.Constants.TimeOutVariable.*;
import static constants.Constants.Urls.*;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public WebElement clickToFind(By locator){
        WebElement btnFind = driver.findElement(locator);
        waitElementIsVisible(btnFind).click();

        return btnFind;
    }
    // метод для входа в аккаунт
    public String login(String username, String password){
        WebElement loginButton = clickToFind(LOGIN_BY);
        driver.findElement(USERNAME).sendKeys(username); // вводим логин
        driver.findElement(PASSWORD).sendKeys(password); // вводим пароль

        driver.findElement(SIGNIN_BY).click(); // нажимаем на вход

        return username;
    }
    // метод, который получает название сайта
    public HomePage getTitle(){
        System.out.println("Заголовок сайта: " + driver.getTitle());

        return this;
    }
    // метод, с помощью которого заходит на курс
    public HomePage firstCourse() throws Exception{
        // Попытка найти и нажать на вкладку "Мои курсы"
        try {
            WebElement findCourse = clickToFind(FIND_COURSE_BY);
        } catch (Exception e) {
            throw new ErrorPage("Не удалось перейти");
        }
        WebElement firstCourse = clickToFind(CLICK_FIRST_COURSE);

        return this;
    }
    // метод, который заходит во вкладку "Личный кабинет"
    public HomePage personalAccount() throws Exception{
        try {
            // находим вкладку "Личный кабинет" и кликаем на нее
            WebElement clickAccount = clickToFind(FIND_PERSONAL_ACCOUNT);
        }catch (Exception e){
            throw new ErrorPage("Не удалось перейти");
        }
        // нажимаем на стрелочку дальше в "Недавно посещенные курсы"
        WebElement clickForNext = clickToFind(CLICK_BUTTON_FOR_NEXT);

        return this;
    }
    // метод, с помощью которого выводит список из элементов главного меню
    public List<WebElement> getMainMemu(){
        WebElement clickIcon = clickToFind(ClICK_ICON);
        // лист для хранения элементов главного меню
        List<WebElement> mainmenus = driver.findElements(FIND_ITEMS_FOR_LIST);

        //возвращаем лист
        return mainmenus;
    }
    // метод, с помощью которого осуществляется выход из аккаунта
    public HomePage exit() throws Exception{
        WebElement clickIcon = clickToFind(ClICK_ICON);
        // находим кнопку выхода и кликаем по ней
        try {
            WebElement exitButton = clickToFind(CLICK_EXIT);
        }catch (Exception e){
            throw new ErrorExit("Ошибка выхода");
        }
        // подтверждаем выход
        WebElement clickConfirmExit = clickToFind(CONFIRM_EXIT);

        return this;
    }
    public boolean errorOfLogin(By errorLocator) throws Exception{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            if (error.getText().equals("Неправильное имя пользователя или пароль.")) {
                throw new ErrorLogin("Вы ввели неверный пароль или логин");
            }
            return false; // Ошибка не обнаружена
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Сообщение об ошибке не найдено.");
            return true; // Ошибка обнаружена
        }
    }
    public static class ErrorLogin extends Exception {
        public ErrorLogin(String message){
            super(message);
        }
    }
    public static class ErrorPage extends Exception {
        public ErrorPage(String message){
            super(message);
        }
    }
    public static class ErrorExit extends Exception {
        public ErrorExit(String message){
            super(message);
        }
    }
}