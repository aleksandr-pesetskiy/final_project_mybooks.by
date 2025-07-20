package by.mybooks.api.registrationForm;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.utils.Utils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class RegistrationForm {

    private final Logger logger = LogManager.getLogger(getClass());
    private final Response response;

    public RegistrationForm(String email, String password, String captcha) {
        response = getResponse(email, password, captcha);
    }

    private Response getResponse(String email, String password, String captcha) {
        return given()
                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .formParam("model", "RegShopForm")
                .formParam("reg_email", email)
                .formParam("reg_password", password)
                .formParam("reg_password_confirmation", password)
                .formParam("captcha", captcha)
                .formParam("_token", Utils.generateToken())
                .when().post(ExpectedMessages.HOME_PAGE_USER + "register");
    }

    public RegistrationForm(String email, String password_reg, String password_conf, String captcha) {
        response = getResponse(email, password_reg, password_conf, captcha);
    }

    private Response getResponse(String email, String password_reg, String password_conf, String captcha) {
        return given()
                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .formParam("model", "RegShopForm")
                .formParam("reg_email", email)
                .formParam("reg_password", password_reg)
                .formParam("reg_password_confirmation", password_conf)
                .formParam("captcha", captcha)
                .formParam("_token", Utils.generateToken())
                .when().post(ExpectedMessages.HOME_PAGE_USER + "register");
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

    public String getContextErrorRegEmail() {
        String contextErrorRegEmail = response.path("errors.reg_email[0]");
        logger.info("Сообщение context по ключу reg_email: {}", contextErrorRegEmail);
        return contextErrorRegEmail;
    }

    public String getContextErrorRegPassword() {
        String contextErrorRegPassword = response.path("errors.reg_password[0]");
        logger.info("Сообщение context по ключу reg_password: {}", contextErrorRegPassword);
        return contextErrorRegPassword;
    }

    public String getContextErrorCaptcha() {
        String contextErrorCaptcha = response.path("errors.captcha[0]");
        logger.info("Сообщение context по ключу captcha: {}", contextErrorCaptcha);
        return contextErrorCaptcha;
    }
}
