package by.mybooks.api;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.api.registrationForm.RegistrationForm;
import by.mybooks.utils.Utils;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationFormTest {

    Faker faker = new Faker();

    @Test
    @DisplayName("Registration with email exists")
    public void testRegistrationWithEmailExists() {
        RegistrationForm registrationForm = new RegistrationForm(ExpectedMessages.EMAIL_TRUE, faker.internet().password(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_EMAIL_EXISTS), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegEmail().contains(ExpectedMessages.MESSAGE_REG_EMAIL_EXISTS), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with small password")
    public void testRegistrationWithSmallPassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), Utils.generateSmallPassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_SMALL), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_SMALL), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with large password")
    public void testRegistrationWithLargePassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), Utils.generateLargePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_LARGE), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_LARGE), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with not match password")
    public void testRegistrationWithNotMatchPassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), faker.internet().password(), faker.internet().password(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with empty email")
    public void testRegistrationWithEmptyEmail() {
        RegistrationForm registrationForm = new RegistrationForm(ExpectedMessages.FIELD_EMPTY, faker.internet().password(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegEmail().contains(ExpectedMessages.MESSAGE_REG_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with empty registration password")
    public void testRegistrationWithEmptyRegPassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), ExpectedMessages.FIELD_EMPTY, faker.internet().password(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with empty confirmation password")
    public void testRegistrationWithEmptyConPassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), faker.internet().password(), ExpectedMessages.FIELD_EMPTY, Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with empty password")
    public void testRegistrationWithEmptyPassword() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), ExpectedMessages.FIELD_EMPTY, Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with empty captcha")
    public void testRegistrationWithEmptyCaptcha() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), faker.internet().password(), ExpectedMessages.FIELD_EMPTY);

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_CAPTCHA_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_REQUIRED), "Message match")
        );
    }

    @Test
    @DisplayName("Registration with invalid captcha")
    public void testRegistrationWithInvalidCaptcha() {
        RegistrationForm registrationForm = new RegistrationForm(faker.internet().emailAddress(), faker.internet().password(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }
}
