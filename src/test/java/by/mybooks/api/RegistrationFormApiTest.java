package by.mybooks.api;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.api.registrationForm.RegistrationForm;
import by.mybooks.utils.Utils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormApiTest {

    @Test
    @DisplayName("Check registration with email exists")
    public void testRegistrationWithEmailExists() {
        RegistrationForm registrationForm = new RegistrationForm(ExpectedMessages.EMAIL_TRUE, Utils.generatePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_EMAIL_EXISTS), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegEmail().contains(ExpectedMessages.MESSAGE_REG_EMAIL_EXISTS), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with small password")
    public void testRegistrationWithSmallPassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generateSmallPassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_SMALL), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_SMALL), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with large password")
    public void testRegistrationWithLargePassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generateLargePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_LARGE), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_LARGE), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with not match password")
    public void testRegistrationWithNotMatchPassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generatePassword(), Utils.generatePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with empty email")
    public void testRegistrationWithEmptyEmail() {
        RegistrationForm registrationForm = new RegistrationForm(ExpectedMessages.FIELD_EMPTY, Utils.generatePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegEmail().contains(ExpectedMessages.MESSAGE_REG_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with empty registration password")
    public void testRegistrationWithEmptyRegPassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), ExpectedMessages.FIELD_EMPTY, Utils.generatePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with empty confirmation password")
    public void testRegistrationWithEmptyConPassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generatePassword(), ExpectedMessages.FIELD_EMPTY, Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_NO_MATCH), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with empty password")
    public void testRegistrationWithEmptyPassword() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), ExpectedMessages.FIELD_EMPTY, Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorRegPassword().contains(ExpectedMessages.MESSAGE_REG_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with empty captcha")
    public void testRegistrationWithEmptyCaptcha() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generatePassword(), ExpectedMessages.FIELD_EMPTY);

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_CAPTCHA_REQUIRED), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_REQUIRED), "Message match")
        );
    }

    @Test
    @DisplayName("Check registration with invalid captcha")
    public void testRegistrationWithInvalidCaptcha() {
        RegistrationForm registrationForm = new RegistrationForm(Utils.generateEmailAddress(), Utils.generatePassword(), Utils.generateCaptcha());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, registrationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(registrationForm.getMessageText().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match"),
                () -> assertTrue(registrationForm.getContextErrorCaptcha().contains(ExpectedMessages.MESSAGE_CAPTCHA_INCORRECT), "Message match")
        );
    }
}
