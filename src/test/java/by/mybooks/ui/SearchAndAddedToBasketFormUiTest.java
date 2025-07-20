package by.mybooks.ui;

import by.mybooks.ui.expectedMessages.ExpectedMessages;
import by.mybooks.ui.pages.searchAndAddedToBasketForm.SearchAndAddedToBasketFormPage;
import by.mybooks.ui.pages.basePage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import by.mybooks.ui.driver.Driver;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAndAddedToBasketFormUiTest {

    public static final int DEFAULT_WAIT_OF_MILLISECONDS = 2000;

    private final Logger logger = LogManager.getLogger(getClass());
    private SearchAndAddedToBasketFormPage searchAndAddedToBasketFormPage;

    @BeforeEach
    public void setUp() {
        logger.info("Инициализация BasePage");
        new BasePage().open();
        searchAndAddedToBasketFormPage = new SearchAndAddedToBasketFormPage();
        searchAndAddedToBasketFormPage.inputFieldSearch(ExpectedMessages.WORD_SEARCH);
        searchAndAddedToBasketFormPage.clickButtonSearch();
    }

    @Test
    @DisplayName("Check search form")
    public void testCheckSearchForm() {
        assertAll(
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.WORD_SEARCH, searchAndAddedToBasketFormPage.getTestInFieldGlobalSearch(), "Text in global field match"),
                () -> Assertions.assertEquals(ExpectedMessages.WORD_SEARCH, searchAndAddedToBasketFormPage.getTestInFieldSearch(), "Text in field match")
        );
    }

    @Test
    @DisplayName("Check search form color element")
    public void testCheckSearchFormColorElement() {
        assertAll(
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_KEY, searchAndAddedToBasketFormPage.getColorKeyPhrase(), "Color text match"),
                () -> Assertions.assertEquals(ExpectedMessages.COLOR_HEADER, searchAndAddedToBasketFormPage.getColorHeader(), "Color header match")
        );
    }

    @Test
    @DisplayName("Check add to basket")
    public void testCheckAddBasket() {
        assertAll(
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> searchAndAddedToBasketFormPage.moveToAddedBasket(),
                () -> searchAndAddedToBasketFormPage.clickButtonToBasket(),
                () -> Thread.sleep(DEFAULT_WAIT_OF_MILLISECONDS),
                () -> Assertions.assertEquals(ExpectedMessages.COUNT, searchAndAddedToBasketFormPage.getCountAddedBasket(), "Count match")
        );
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие BasePage");
        Driver.quit();
    }
}
