package by.mybooks.utils;

import com.github.javafaker.Faker;

import static java.lang.String.format;

public class Utils {

    public static int LENGTH_PASSWORD_SMALL_MIN = 1;
    public static int LENGTH_PASSWORD_SMALL_MAX = 3;
    public static int LENGTH_PASSWORD_LARGE_MIN = 256;
    public static int LENGTH_PASSWORD_LARGE_MAX = 260;
    public static int LENGTH_NAME_EMAIL = 245;
    public static int LENGTH_DOMAIN_EMAIL_FIRST = 10;
    public static int LENGTH_DOMAIN_EMAIL_SECOND = 3;
    public static int LENGTH_CAPTCHA = 6;
    public static int LENGTH_TOKEN = 40;

    static Faker faker = new Faker();

    public static String generateLargeEmail() {
        return faker.regexify(format("[a-z]{%s}\\@[a-z]{%s}\\.[a-z]{%s}", LENGTH_NAME_EMAIL, LENGTH_DOMAIN_EMAIL_FIRST, LENGTH_DOMAIN_EMAIL_SECOND));
    }

    public static String generateLargePassword() {
        return faker.internet().password(LENGTH_PASSWORD_LARGE_MIN, LENGTH_PASSWORD_LARGE_MAX);
    }

    public static String generateSmallPassword() {
        return faker.internet().password(LENGTH_PASSWORD_SMALL_MIN, LENGTH_PASSWORD_SMALL_MAX);
    }

    public static String generateCaptcha() {
        return faker.regexify(format("[0-9]{%s}", LENGTH_CAPTCHA));
    }

    public static String generateToken() {
        return faker.regexify(format("[a-zA-Z0-9]{%s}", LENGTH_TOKEN));
    }
}
