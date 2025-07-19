package by.mybooks.ui.pages.registrationForm;

import by.mybooks.ui.pages.basePage.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class RegistrationFormPage extends BasePage {

    public void clickButtonRegistration() {
        logger.info("Клик по кнопке для перехода на регистрацию");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION)).click();
    }

    public String getPlaceholderLoginText() {
        String placeholderText = driver.findElement(By.xpath(RegistrationFormLocator.FIELD_LOGIN_PLACEHOLDER)).getText();
        logger.info("Текст placeholder для email: {}", placeholderText);
        return placeholderText;
    }

    public String getPlaceholderPasswordText() {
        String placeholderText = driver.findElement(By.xpath(RegistrationFormLocator.FIELD_PASSWORD_PLACEHOLDER)).getText();
        logger.info("Текст placeholder для password: {}", placeholderText);
        return placeholderText;
    }

    public String getPlaceholderCaptchaText() {
        String placeholderText = driver.findElement(By.xpath(RegistrationFormLocator.FIELD_CAPTCHA_PLACEHOLDER)).getText();
        logger.info("Текст placeholder для captcha: {}", placeholderText);
        return placeholderText;
    }

    public String getButtonLoginText() {
        String buttonName = driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_LOGIN)).getText();
        logger.info("Название кнопки перехода для входа: {}", buttonName);
        return buttonName;
    }

    public String getButtonRegistrationText() {
        String buttonName = driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION)).getText();
        logger.info("Название кнопки перехода для регистрации: {}", buttonName);
        return buttonName;
    }

    public String getButtonRegistrationConfirmationText() {
        String buttonName = driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_CONFIRMATION)).getText();
        logger.info("Название кнопки завершения регистрации: {}", buttonName);
        return buttonName;
    }

    public String getNameUserAgreement() {
        String nameUserAgreement = driver.findElement(By.xpath(RegistrationFormLocator.USER_AGREEMENT)).getAttribute("title");
        logger.info("Название документа: {}", nameUserAgreement);
        return nameUserAgreement;
    }

    public void clickButtonRegistrationConfirmation() {
        logger.info("Клик по кнопке Зарегистрироваться");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_CONFIRMATION)).click();
    }

    public void inputFieldLogin(String login) {
        logger.info("Ввод email: {}", login);
        driver.findElement(By.xpath(RegistrationFormLocator.FIELD_INPUT_LOGIN)).sendKeys(login);
    }

    public void inputFieldPassword(String password) {
        logger.info("Ввод password: {}", password);
        driver.findElement(By.xpath(RegistrationFormLocator.FIELD_INPUT_PASSWORD)).sendKeys(password);
    }

    public void inputFieldCaptcha(String captcha) {
        logger.info("Ввод captcha: {}", captcha);
        driver.findElement(By.xpath(RegistrationFormLocator.FIELD_INPUT_CAPTCHA)).sendKeys(captcha);
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        logger.info("Текст alert: {}", alertText);
        alert.accept();
        logger.info("Подтверждение alert");
        return alertText;
    }

    public String getColorLineLoginOk() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_LOGIN_OK)).getCssValue("color");
        logger.info("Цвет линии под email без ошибки в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLineLoginError() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_LOGIN_ERROR)).getCssValue("color");
        logger.info("Цвет линии под email с ошибкой в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLinePasswordOk() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_PASSWORD_OK)).getCssValue("color");
        logger.info("Цвет линии под password без ошибки в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLinePasswordError() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_PASSWORD_ERROR)).getCssValue("color");
        logger.info("Цвет линии под password с ошибкой в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLineCaptchaOk() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_CAPTCHA_OK)).getCssValue("color");
        logger.info("Цвет линии под captcha без ошибки в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorLineCaptchaError() {
        String colorLine = driver.findElement(By.xpath(RegistrationFormLocator.LINE_UNDER_CAPTCHA_ERROR)).getCssValue("color");
        logger.info("Цвет линии под captcha с ошибкой в RGB: {}", colorLine);
        return colorLine;
    }
}
