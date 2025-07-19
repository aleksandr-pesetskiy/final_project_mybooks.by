package by.mybooks.ui.pages.registrationForm;

public class RegistrationFormLocator {

    public static final String FIELD_LOGIN_PLACEHOLDER = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='reg_email']/../div";
    public static final String FIELD_PASSWORD_PLACEHOLDER = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='reg_password']/../div[@class='ok-label-float']";
    public static final String FIELD_CAPTCHA_PLACEHOLDER = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='captcha']/../div";
    public static final String BUTTON_LOGIN = "//li[@href='#enter']";
    public static final String BUTTON_REGISTRATION = "//li[@href='#registration']";
    public static final String BUTTON_REGISTRATION_CONFIRMATION = "//button[@id='sing_up_btn_shop']";
    public static final String FIELD_INPUT_LOGIN = "//div[@id='registration']//input[@name='reg_email']";
    public static final String FIELD_INPUT_PASSWORD = "//div[@id='registration']//input[@name='reg_password']";
    public static final String FIELD_INPUT_CAPTCHA = "//div[@id='registration']//input[@name='captcha']";
    public static final String USER_AGREEMENT = "//a[@href='/terms-of-use.xhtml']";
    public static final String LINE_UNDER_LOGIN_OK = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='reg_email']/../span";
    public static final String LINE_UNDER_LOGIN_ERROR = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check -state-error']/input[@name='reg_email']/../span";
    public static final String LINE_UNDER_PASSWORD_OK = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='reg_password']/../span";
    public static final String LINE_UNDER_PASSWORD_ERROR = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check -state-error']/input[@name='reg_password']/../span";
    public static final String LINE_UNDER_CAPTCHA_OK = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check']/input[@name='captcha']/../span";
    public static final String LINE_UNDER_CAPTCHA_ERROR = "//div[@id='registration']//div[@class='ok-form-row -input-special data-input-check -state-error']/input[@name='captcha']/../span";
}
