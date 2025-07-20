package by.mybooks.api.authorizationForm;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.utils.Utils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class AuthorizationForm {

    private final Logger logger = LogManager.getLogger(getClass());
    private final Response response;

    public AuthorizationForm(String email, String password) {
        response = getResponse(email, password);
    }

    private Response getResponse(String email, String password) {
        return given()
                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .formParam("login", "login")
                .formParam("type", "email_password")
                .formParam("email", email)
                .formParam("password", password)
                .formParam("_token", Utils.generateToken())
                .when().post(ExpectedMessages.HOME_PAGE_USER + "auth");
    }

    public int getStatusCode() {
        int statusCode = response.getStatusCode();
        logger.info("Статус код: {}", statusCode);
        return statusCode;
    }

    public String getMessageText() {
        String messageText = response.path("message");
        logger.info("Текст сообщения: {}", messageText);
        return messageText;
    }

    public String getContextErrorEmail() {
        String contextErrorEmail = response.path("errors.email[0]");
        logger.info("Сообщение context по ключу email: {}", contextErrorEmail);
        return contextErrorEmail;
    }

    public String getContextErrorPassword() {
        String contextErrorPassword = response.path("errors.password[0]");
        logger.info("Сообщение context по ключу password: {}", contextErrorPassword);
        return contextErrorPassword;
    }
}
