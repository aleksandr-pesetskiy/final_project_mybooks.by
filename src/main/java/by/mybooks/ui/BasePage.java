package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import by.mybooks.ui.driver.Driver;
import by.mybooks.ui.pages.authorizationForm.AuthorizationFormLocator;

public class BasePage {

    protected final WebDriver driver = Driver.getDriver();
    protected final Logger logger = LogManager.getLogger(getClass());

    public void open() {
        logger.info("Открытие главной страницы: {}", ExpectedMessages.HOME_PAGE);
        driver.get(ExpectedMessages.HOME_PAGE);
        try {
            clickButtonAcceptCookie();
            logger.info("Cookie закрыты");
            clickButtonCabinet();
            logger.info("Открыта форма авторизации");
        } catch (Exception e) {
            logger.warn("Не удалось принять Cookie: {}", e.getMessage());
        }
    }

    public void clickButtonAcceptCookie() {
        logger.info("Принятие Cookie");
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_ACCEPT_COOKIE)).click();
    }

    public void clickButtonCabinet() {
        logger.info("Открытие формы авторизации");
        driver.findElement(By.xpath(AuthorizationFormLocator.BUTTON_CABINET)).click();
    }
}
