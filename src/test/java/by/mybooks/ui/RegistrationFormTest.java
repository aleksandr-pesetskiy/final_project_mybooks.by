package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import by.mybooks.ui.pages.basePage.BasePage;
import by.mybooks.ui.pages.registrationForm.RegistrationFormPage;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import by.mybooks.ui.driver.Driver;

import static org.junit.jupiter.api.Assertions.assertAll;

public class RegistrationFormTest {
    public static final int DEFAULT_WAIT_OF_MILLISECONDS = 1000;

    private final Logger logger = LogManager.getLogger(getClass());
    private RegistrationFormPage registrationFormPage;

    Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        logger.info("Инициализация BasePage");
        new BasePage().open();
        registrationFormPage = new RegistrationFormPage();
    }

    @Test
    @DisplayName("Check authorization form")
    public void testCheckAuthorizationForm() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_TEXT_PLACEHOLDER_EMAIL, registrationFormPage.getPlaceholderLoginText(), "Placeholder login match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_TEXT_PLACEHOLDER_PASSWORD, registrationFormPage.getPlaceholderPasswordText(), "Placeholder password match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_NAME_BUTTON_LOGIN, registrationFormPage.getButtonLoginText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_NAME_BUTTON_REGISTRATION, registrationFormPage.getLinkRegistrationText(), "Name button match")
        );
    }

    @Test
    @DisplayName("Check authorization without login")
    public void checkAuthorizationWithoutLogin() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, registrationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, registrationFormPage.getColorLinePassword(), "Color element match"),
                () -> registrationFormPage.inputFieldPassword(faker.internet().password()),
                () -> registrationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, registrationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, registrationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие BasePage");
        Driver.quit();
    }
}
