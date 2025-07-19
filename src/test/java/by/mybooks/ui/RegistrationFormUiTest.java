package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import by.mybooks.ui.pages.basePage.BasePage;
import by.mybooks.ui.pages.registrationForm.RegistrationFormPage;
import by.mybooks.utils.Utils;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import by.mybooks.ui.driver.Driver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationFormUiTest {
    public static final int DEFAULT_WAIT_OF_MILLISECONDS = 1000;

    private final Logger logger = LogManager.getLogger(getClass());
    private RegistrationFormPage registrationFormPage;

    Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        logger.info("Инициализация BasePage");
        new BasePage().open();
        registrationFormPage = new RegistrationFormPage();
        logger.info("Открытие формы регистрации");
        registrationFormPage.clickButtonRegistration();
        logger.info("Открыта форма регистрации");
    }

    @Test
    @DisplayName("Check registration form")
    public void testCheckRegistrationForm() {
        assertAll(
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_PLACEHOLDER_EMAIL, registrationFormPage.getPlaceholderLoginText(), "Placeholder login match"),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_PLACEHOLDER_PASSWORD, registrationFormPage.getPlaceholderPasswordText(), "Placeholder password match"),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_PLACEHOLDER_CAPTCHA, registrationFormPage.getPlaceholderCaptchaText(), "Placeholder captcha match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_LOGIN, registrationFormPage.getButtonLoginText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_REGISTRATION, registrationFormPage.getButtonRegistrationText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_REGISTRATION_CONFIRMATION, registrationFormPage.getButtonRegistrationConfirmationText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_USER_AGREEMENT, registrationFormPage.getNameUserAgreement(), "Name button match")
        );
    }

    @Test
    @DisplayName("Check registration without login")
    public void checkRegistrationWithoutLogin() {
        assertAll(
                () -> registrationFormPage.inputFieldPassword(faker.internet().password()),
                () -> registrationFormPage.inputFieldCaptcha(Utils.generateCaptcha()),
                () -> registrationFormPage.clickButtonRegistrationConfirmation(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> assertTrue(registrationFormPage.getAlertText().contains(ExpectedMessages.TEXT_ALERT_EMPTY_EMAIL), "Alert message match")
        );
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие BasePage");
        Driver.quit();
    }
}
