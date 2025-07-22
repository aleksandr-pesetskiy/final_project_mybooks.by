package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import by.mybooks.ui.pages.authorizationForm.AuthorizationFormPage;
import by.mybooks.ui.pages.basePage.BasePage;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import by.mybooks.ui.driver.Driver;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationFormUiTest {

    public static final int DEFAULT_WAIT_OF_MILLISECONDS = 1000;

    private final Logger logger = LogManager.getLogger(getClass());
    private AuthorizationFormPage authorizationFormPage;

    Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        logger.info("Инициализация BasePage");
        new BasePage().open();
        authorizationFormPage = new AuthorizationFormPage();
    }

    @Test
    @DisplayName("Check authorization form")
    public void testAuthorizationForm() {
        assertAll(
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_PLACEHOLDER_EMAIL, authorizationFormPage.getPlaceholderLoginText(), "Placeholder login match"),
                () -> Assertions.assertEquals(ExpectedMessages.TEXT_PLACEHOLDER_PASSWORD, authorizationFormPage.getPlaceholderPasswordText(), "Placeholder password match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_LOGIN, authorizationFormPage.getButtonLoginText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_REGISTRATION, authorizationFormPage.getButtonRegistrationText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.NAME_BUTTON_LOGIN_CONFIRMATION, authorizationFormPage.getButtonLoginConfirmationText(), "Name button match")
        );
    }

    @Test
    @DisplayName("Check authorization without login")
    public void testAuthorizationWithoutLogin() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLoginConfirmation(),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization without password")
    public void testCheckAuthorizationWithoutPassword() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(faker.internet().emailAddress()),
                () -> authorizationFormPage.clickButtonLoginConfirmation(),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization with empty fields")
    public void testAuthorizationWithEmptyFields() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.clickButtonLoginConfirmation(),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization for user not found")
    public void testAuthorizationUserNotFound() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(faker.internet().emailAddress()),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLoginConfirmation(),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization for user invalid password")
    public void testAuthorizationUserInvalidPassword() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(ExpectedMessages.EMAIL_TRUE),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLoginConfirmation(),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOk(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие BasePage");
        Driver.quit();
    }
}
