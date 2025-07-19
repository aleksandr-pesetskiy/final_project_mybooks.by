package by.mybooks.ui.pages.authorizationForm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import by.mybooks.ui.driver.Driver;

public class AuthorizationFormPage {

    private final WebDriver driver;

    public AuthorizationFormPage() {
        driver = Driver.getDriver();
    }

    public String getPlaceholderLoginText() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_LOGIN_PLACEHOLDER)).getText();
    }

    public String getPlaceholderPasswordText() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_PASSWORD_PLACEHOLDER)).getText();
    }

    public String getButtonLoginText() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_LOGIN)).getText();
    }

    public String getLinkRegistrationText() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.TITLE_LINK_REGISTRATION)).getText();
    }

    public void clickButtonLogin() {
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_LOGIN)).click();
    }

    public void inputFieldLogin(String login) {
        driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_INPUT_LOGIN)).sendKeys(login);
    }

    public void inputFieldPassword(String password) {
        driver.findElement(By.xpath(AuthorizationFormLocator.FIELD_INPUT_PASSWORD)).sendKeys(password);
    }

    public String getColorLineLoginOK() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_LOGIN_OK)).getCssValue("color");
    }

    public String getColorLineLoginError() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_LOGIN_ERROR)).getCssValue("color");
    }

    public String getColorLinePassword() {
        return driver.findElement(By.xpath(AuthorizationFormLocator.LINE_UNDER_PASSWORD)).getCssValue("color");
    }
}
