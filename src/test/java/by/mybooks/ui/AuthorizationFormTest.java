package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import by.mybooks.ui.driver.Driver;
import by.mybooks.ui.pages.authorizationForm.AuthorizationFormPage;

import static org.junit.jupiter.api.Assertions.assertAll;

public class AuthorizationFormTest {
    public static final int DEFAULT_WAIT_OF_MILLISECONDS = 1000;

    private AuthorizationFormPage authorizationFormPage;

    Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        new BasePage().open();
        authorizationFormPage = new AuthorizationFormPage();
    }

    @Test
    @DisplayName("Check authorization form")
    public void testCheckAuthorizationForm() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_TEXT_PLACEHOLDER_EMAIL, authorizationFormPage.getPlaceholderLoginText(), "Placeholder login match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_TEXT_PLACEHOLDER_PASSWORD, authorizationFormPage.getPlaceholderPasswordText(), "Placeholder password match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_NAME_BUTTON_LOGIN, authorizationFormPage.getButtonLoginText(), "Name button match"),
                () -> Assertions.assertEquals(ExpectedMessages.DEFAULT_NAME_BUTTON_REGISTRATION, authorizationFormPage.getLinkRegistrationText(), "Name button match")
        );
    }

    @Test
    @DisplayName("Check authorization without login")
    public void checkAuthorizationWithoutLogin() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization without password")
    public void testCheckAuthorizationWithoutPassword() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(faker.internet().emailAddress()),
                () -> authorizationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization with empty fields")
    public void checkAuthorizationWithEmptyFields() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization for user not found")
    public void checkAuthorizationUserNotFound() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(faker.internet().emailAddress()),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLineLoginError(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @Test
    @DisplayName("Check authorization for user invalid password")
    public void checkAuthorizationUserInvalidPassword() {
        assertAll(
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLinePassword(), "Color element match"),
                () -> authorizationFormPage.inputFieldLogin(ExpectedMessages.EMAIL_TRUE),
                () -> authorizationFormPage.inputFieldPassword(faker.internet().password()),
                () -> authorizationFormPage.clickButtonLogin(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_WHITE, authorizationFormPage.getColorLineLoginOK(), "Color element match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_ELEMENT_RED, authorizationFormPage.getColorLinePassword(), "Color element match")
        );
    }

    @AfterEach
    public void tearDown() {
        Driver.quit();
    }
}
