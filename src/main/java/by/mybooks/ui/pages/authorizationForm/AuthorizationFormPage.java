package by.mybooks.ui.pages.authorizationForm;

import by.mybooks.ui.BasePage;
import org.openqa.selenium.By;

public class AuthorizationFormPage extends BasePage {

    public String getPlaceholderLoginText() {
        String placeholderText = driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_LOGIN_PLACEHOLDER)).getText();
        logger.info("Текст placeholder для email: {}", placeholderText);
        return placeholderText;
    }

    public String getPlaceholderPasswordText() {
        String placeholderText = driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_PASSWORD_PLACEHOLDER)).getText();
        logger.info("Текст placeholder для password: {}", placeholderText);
        return placeholderText;
    }

    public String getButtonLoginText() {
        String buttonName = driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_LOGIN)).getText();
        logger.info("Название кнопки для входа: {}", buttonName);
        return buttonName;
    }

    public String getLinkRegistrationText() {
        String buttonName = driver.findElement(By.xpath(AuthorizationFormLocator.TITLE_LINK_REGISTRATION)).getText();
        logger.info("Название кнопки для регистрации: {}", buttonName);
        return buttonName;
    }

    public void clickButtonLogin() {
        logger.info("Клик по кнопке Вход");
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_LOGIN)).click();
    }

    public void inputFieldLogin(String login) {
        logger.info("Ввод email: {}", login);
        driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_INPUT_LOGIN)).sendKeys(login);
    }

    public void inputFieldPassword(String password) {
        logger.info("Ввод password: {}", password);
        driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_INPUT_PASSWORD)).sendKeys(password);
    }

    public String getColorLineLoginOK() {
        String colorLine = driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_LOGIN_OK)).getCssValue("color");
        logger.info("Цвет линии под email без ошибки в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLineLoginError() {
        String colorLine = driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_LOGIN_ERROR)).getCssValue("color");
        logger.info("Цвет линии под email с ошибкой в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLinePassword() {
        String colorLine = driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_PASSWORD)).getCssValue("color");
        logger.info("Цвет линии под password в RGB: {}", colorLine);
        return colorLine;
    }
}
