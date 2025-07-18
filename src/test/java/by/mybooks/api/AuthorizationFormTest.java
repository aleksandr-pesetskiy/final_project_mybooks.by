package by.mybooks.api;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.api.authorizationForm.AuthorizationForm;
import by.mybooks.utils.Utils;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationFormTest {

    Faker faker = new Faker();

    @Test
    @DisplayName("Login with wrong email")
    public void testLoginWithWrongEmail() {
        AuthorizationForm authorizationForm = new AuthorizationForm(faker.internet().emailAddress(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_EMAIL_INCORRECT), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorEmail().contains(ExpectedMessages.MESSAGE_EMAIL_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Login with wrong password")
    public void testLoginWithWrongPassword() {
        AuthorizationForm authorizationForm = new AuthorizationForm(ExpectedMessages.EMAIL_TRUE, faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_PASSWORD_INCORRECT), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorPassword().contains(ExpectedMessages.MESSAGE_PASSWORD_INCORRECT), "Message match")
        );
    }

    @Test
    @DisplayName("Login with large email")
    public void testLoginWithLargeEmail() {
        AuthorizationForm authorizationForm = new AuthorizationForm(Utils.generateLargeEmail(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_EMAIl_LARGE), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorEmail().contains(ExpectedMessages.MESSAGE_EMAIl_LARGE), "Message match")
        );
    }

    @Test
    @DisplayName("Login with large password")
    public void testLoginWithLargePassword() {
        AuthorizationForm authorizationForm = new AuthorizationForm(ExpectedMessages.EMAIL_TRUE, Utils.generateLargePassword());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_PASSWORD_LARGE), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorPassword().contains(ExpectedMessages.MESSAGE_PASSWORD_LARGE), "Message match")
        );
    }

    @Test
    @DisplayName("Login with empty email")
    public void testLoginWithEmptyEmail() {
        AuthorizationForm authorizationForm = new AuthorizationForm(ExpectedMessages.FIELD_EMPTY, faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorEmail().contains(ExpectedMessages.MESSAGE_EMAIL_REQUIRED), "Message match")
        );
    }

    @Test
    @DisplayName("Login with empty password")
    public void testLoginWithEmptyPassword() {
        AuthorizationForm authorizationForm = new AuthorizationForm(ExpectedMessages.EMAIL_TRUE, ExpectedMessages.FIELD_EMPTY);

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_PASSWORD_REQUIRED), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorPassword().contains(ExpectedMessages.MESSAGE_PASSWORD_REQUIRED), "Message match")
        );
    }

    @Test
    @DisplayName("Login with empty email and password")
    public void testLoginWithEmptyEmailAndPassword() {
        AuthorizationForm authorizationForm = new AuthorizationForm(ExpectedMessages.FIELD_EMPTY, ExpectedMessages.FIELD_EMPTY);

        assertAll(
                () -> assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, authorizationForm.getStatusCode(), "StatusCode should be 422"),
                () -> assertTrue(authorizationForm.getMessageText().contains(ExpectedMessages.MESSAGE_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorEmail().contains(ExpectedMessages.MESSAGE_EMAIL_REQUIRED), "Message match"),
                () -> assertTrue(authorizationForm.getContextErrorPassword().contains(ExpectedMessages.MESSAGE_PASSWORD_REQUIRED), "Message match")
        );
    }
}
