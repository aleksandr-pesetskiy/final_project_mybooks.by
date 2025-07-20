package by.mybooks.ui.pages.searchAndAddedToBasketForm;

import by.mybooks.ui.pages.basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class SearchAndAddedToBasketFormPage extends BasePage {

    public void inputFieldSearch(String searchWord) {
        logger.info("Ввод слова поиска: {}", searchWord);
        driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.FIELD_SEARCH)).sendKeys(searchWord);
    }

    public void clickButtonSearch() {
        logger.info("Клик по кнопке поиска");
        driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.BUTTON_SEARCH)).click();
    }

    public String getTestInFieldSearch() {
        String textField = driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.TEXT_IN_FIELD)).getAttribute("value");
        logger.info("Текст в поле поиска: {}", textField);
        return textField;
    }

    public String getTestInFieldGlobalSearch() {
        String textField = driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.TEXT_IN_FIELD)).getAttribute("value");
        logger.info("Текст в поле глобального поиска: {}", textField);
        return textField;
    }

    public String getColorKeyPhrase() {
        String colorLine = driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.TEXT_KEY_PHRASE)).getCssValue("color");
        logger.info("Цвет слова 'Ключевая фраза' в RGB: {}", colorLine);
        return colorLine;
    }

    public String getColorHeader() {
        String colorLine = driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.TEXT_HEADER)).getCssValue("color");
        logger.info("Цвет заголовка в RGB: {}", colorLine);
        return colorLine;
    }

    public void moveToAddedBasket() {
        Actions actions = new Actions(driver);
        logger.info("Перемещение мышки к кнопке добавления в корзину");
        actions.moveToElement(driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.FORM_ADDED_BASKET))).perform();
    }

    public void clickButtonToBasket() {
        logger.info("Клик по кнопке 'В корзину'");
        driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.BUTTON_TO_BASKET)).click();
    }

    public String getCountAddedBasket() {
        String count = driver.findElement(By.xpath(SearchAndAddedToBasketFormLocator.COUNT_ADDED_BASKET)).getText();
        logger.info("Количество товаров в корзине: {}", count);
        return count;
    }
}
