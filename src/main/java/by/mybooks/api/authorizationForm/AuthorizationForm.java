package by.mybooks.api.authorizationForm;

import by.mybooks.api.expectedMessages.ExpectedMessages;
import by.mybooks.utils.Utils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorizationForm {

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
        return response.getStatusCode();
    }

    public String getMessageText() {
        return response.path("message");
    }

    public String getContextErrorEmail() {
        return response.path("errors.email[0]");
    }

    public String getContextErrorPassword() {
        return response.path("errors.password[0]");
    }
}
