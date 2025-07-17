package by.mybooks.api.registrationForm;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.utils.Utils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationForm {

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
        return response.getStatusCode();
    }

    public String getMessageText() {
        return response.path("message");
    }

    public String getContextErrorRegEmail() {
        return response.path("errors.reg_email[0]");
    }

    public String getContextErrorRegPassword() {
        return response.path("errors.reg_password[0]");
    }

    public String getContextErrorCaptcha() {
        return response.path("errors.captcha[0]");
    }
}
