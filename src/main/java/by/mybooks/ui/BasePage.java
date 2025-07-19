package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import by.mybooks.ui.driver.Driver;
import by.mybooks.ui.pages.authorizationForm.AuthorizationFormLocator;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        driver = Driver.getDriver();
    }

    public void open() {
        driver.get(ExpectedMessages.HOME_PAGE);
        try {
            clickButtonAcceptCookie();
            clickButtonCabinet();
            getAuthorizationForm();
        } catch (Exception ignored) {
        }
    }

    public void clickButtonAcceptCookie() {
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_ACCEPT_COOKIE)).click();
    }

    public void clickButtonCabinet() {
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_CABINET)).click();
    }

    public void getAuthorizationForm() {
        driver.findElement(By.xpath(AuthorizationFormLocator.WINDOW_MODAL_AUTHORIZATION));
    }
}
