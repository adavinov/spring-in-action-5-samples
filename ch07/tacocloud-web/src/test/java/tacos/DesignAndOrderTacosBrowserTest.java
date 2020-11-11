package tacos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Ignore("Ignore for now...deal with security implications in tests.")
public class DesignAndOrderTacosBrowserTest {

    private static HtmlUnitDriver browser;

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate rest;

    @BeforeClass
    public static void setup() {
        browser = new HtmlUnitDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void testDesignATacoPage_HappyPath() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertLandedOnLoginPage();
        doRegistration("testuser", "testpassword");
        assertLandedOnLoginPage();
        doLogin("testuser", "testpassword");
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        clickBuildAnotherTaco();
        buildAndSubmitATaco("Another Taco", "COTO", "CARN", "JACK", "LETC", "SRCR");
        fillInAndSubmitOrderForm();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        doLogout();
    }

    @Test
    public void testDesignATacoPage_EmptyOrderInfo() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertLandedOnLoginPage();
        doRegistration("testuser2", "testpassword");
        doLogin("testuser2", "testpassword");
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        submitEmptyOrderForm();
        fillInAndSubmitOrderForm();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        doLogout();
    }

    @Test
    public void testDesignATacoPage_InvalidOrderInfo() throws Exception {
        browser.get(homePageUrl());
        clickDesignATaco();
        assertLandedOnLoginPage();
        doRegistration("testuser3", "testpassword");
        doLogin("testuser3", "testpassword");
        assertDesignPageElements();
        buildAndSubmitATaco("Basic Taco", "FLTO", "GRBF", "CHED", "TMTO", "SLSA");
        submitInvalidOrderForm();
        fillInAndSubmitOrderForm();
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        doLogout();
    }

    //
    // Browser test action methods
    //
    private void buildAndSubmitATaco(final String name, final String... ingredients) {
        assertDesignPageElements();

        for (final String ingredient : ingredients) {
            browser.findElementByCssSelector("input[value='" + ingredient + "']").click();
        }
        browser.findElementByCssSelector("input#name").sendKeys(name);
        browser.findElementByCssSelector("form#tacoForm").submit();
    }

    private void assertLandedOnLoginPage() {
        assertEquals(loginPageUrl(), browser.getCurrentUrl());
    }

    private void doRegistration(final String username, final String password) {
        browser.findElementByLinkText("here").click();
        assertEquals(registrationPageUrl(), browser.getCurrentUrl());
        browser.findElementByName("username").sendKeys(username);
        browser.findElementByName("password").sendKeys(password);
        browser.findElementByName("confirm").sendKeys(password);
        browser.findElementByName("fullname").sendKeys("Test McTest");
        browser.findElementByName("street").sendKeys("1234 Test Street");
        browser.findElementByName("city").sendKeys("Testville");
        browser.findElementByName("state").sendKeys("TX");
        browser.findElementByName("zip").sendKeys("12345");
        browser.findElementByName("phone").sendKeys("123-123-1234");
        browser.findElementByCssSelector("form#registerForm").submit();
    }

    private void doLogin(final String username, final String password) {
        browser.findElementByName("username").sendKeys(username);
        browser.findElementByName("password").sendKeys(password);
        browser.findElementByCssSelector("form#loginForm").submit();
    }

    private void doLogout() {
        final WebElement logoutForm = browser.findElementByCssSelector("form#logoutForm");
        if (logoutForm != null) {
            logoutForm.submit();
        }
    }

    private void assertDesignPageElements() {
        assertEquals(designPageUrl(), browser.getCurrentUrl());
        final List<WebElement> ingredientGroups = browser.findElementsByClassName("ingredient-group");
        assertEquals(5, ingredientGroups.size());

        final WebElement wrapGroup = browser.findElementByCssSelector("div.ingredient-group#wraps");
        final List<WebElement> wraps = wrapGroup.findElements(By.tagName("div"));
        assertEquals(2, wraps.size());
        assertIngredient(wrapGroup, 0, "FLTO", "Flour Tortilla");
        assertIngredient(wrapGroup, 1, "COTO", "Corn Tortilla");

        final WebElement proteinGroup = browser.findElementByCssSelector("div.ingredient-group#proteins");
        final List<WebElement> proteins = proteinGroup.findElements(By.tagName("div"));
        assertEquals(2, proteins.size());
        assertIngredient(proteinGroup, 0, "GRBF", "Ground Beef");
        assertIngredient(proteinGroup, 1, "CARN", "Carnitas");

        final WebElement cheeseGroup = browser.findElementByCssSelector("div.ingredient-group#cheeses");
        final List<WebElement> cheeses = proteinGroup.findElements(By.tagName("div"));
        assertEquals(2, cheeses.size());
        assertIngredient(cheeseGroup, 0, "CHED", "Cheddar");
        assertIngredient(cheeseGroup, 1, "JACK", "Monterrey Jack");

        final WebElement veggieGroup = browser.findElementByCssSelector("div.ingredient-group#veggies");
        final List<WebElement> veggies = proteinGroup.findElements(By.tagName("div"));
        assertEquals(2, veggies.size());
        assertIngredient(veggieGroup, 0, "TMTO", "Diced Tomatoes");
        assertIngredient(veggieGroup, 1, "LETC", "Lettuce");

        final WebElement sauceGroup = browser.findElementByCssSelector("div.ingredient-group#sauces");
        final List<WebElement> sauces = proteinGroup.findElements(By.tagName("div"));
        assertEquals(2, sauces.size());
        assertIngredient(sauceGroup, 0, "SLSA", "Salsa");
        assertIngredient(sauceGroup, 1, "SRCR", "Sour Cream");
    }

    private void fillInAndSubmitOrderForm() {
        assertTrue(browser.getCurrentUrl().startsWith(orderDetailsPageUrl()));
        fillField("input#deliveryName", "Ima Hungry");
        fillField("input#deliveryStreet", "1234 Culinary Blvd.");
        fillField("input#deliveryCity", "Foodsville");
        fillField("input#deliveryState", "CO");
        fillField("input#deliveryZip", "81019");
        fillField("input#ccNumber", "4111111111111111");
        fillField("input#ccExpiration", "10/19");
        fillField("input#ccCVV", "123");
        browser.findElementByCssSelector("form#orderForm").submit();
    }

    private void submitEmptyOrderForm() {
        assertEquals(currentOrderDetailsPageUrl(), browser.getCurrentUrl());
        // clear fields automatically populated from user profile
        fillField("input#deliveryName", "");
        fillField("input#deliveryStreet", "");
        fillField("input#deliveryCity", "");
        fillField("input#deliveryState", "");
        fillField("input#deliveryZip", "");
        browser.findElementByCssSelector("form#orderForm").submit();

        assertEquals(orderDetailsPageUrl(), browser.getCurrentUrl());

        final List<String> validationErrors = getValidationErrorTexts();
        assertEquals(9, validationErrors.size());
        assertTrue(validationErrors.contains("Please correct the problems below and resubmit."));
        assertTrue(validationErrors.contains("Delivery name is required"));
        assertTrue(validationErrors.contains("Street is required"));
        assertTrue(validationErrors.contains("City is required"));
        assertTrue(validationErrors.contains("State is required"));
        assertTrue(validationErrors.contains("Zip code is required"));
        assertTrue(validationErrors.contains("Not a valid credit card number"));
        assertTrue(validationErrors.contains("Must be formatted MM/YY"));
        assertTrue(validationErrors.contains("Invalid CVV"));
    }

    private List<String> getValidationErrorTexts() {
        final List<WebElement> validationErrorElements = browser.findElementsByClassName("validationError");
        final List<String> validationErrors = validationErrorElements.stream().map(el -> el.getText())
                .collect(Collectors.toList());
        return validationErrors;
    }

    private void submitInvalidOrderForm() {
        assertTrue(browser.getCurrentUrl().startsWith(orderDetailsPageUrl()));
        fillField("input#deliveryName", "I");
        fillField("input#deliveryStreet", "1");
        fillField("input#deliveryCity", "F");
        fillField("input#deliveryState", "C");
        fillField("input#deliveryZip", "8");
        fillField("input#ccNumber", "1234432112344322");
        fillField("input#ccExpiration", "14/91");
        fillField("input#ccCVV", "1234");
        browser.findElementByCssSelector("form#orderForm").submit();

        assertEquals(orderDetailsPageUrl(), browser.getCurrentUrl());

        final List<String> validationErrors = getValidationErrorTexts();
        assertEquals(4, validationErrors.size());
        assertTrue(validationErrors.contains("Please correct the problems below and resubmit."));
        assertTrue(validationErrors.contains("Not a valid credit card number"));
        assertTrue(validationErrors.contains("Must be formatted MM/YY"));
        assertTrue(validationErrors.contains("Invalid CVV"));
    }

    private void fillField(final String fieldName, final String value) {
        final WebElement field = browser.findElementByCssSelector(fieldName);
        field.clear();
        field.sendKeys(value);
    }

    private void assertIngredient(final WebElement ingredientGroup, final int ingredientIdx, final String id, final String name) {
        final List<WebElement> proteins = ingredientGroup.findElements(By.tagName("div"));
        final WebElement ingredient = proteins.get(ingredientIdx);
        assertEquals(id, ingredient.findElement(By.tagName("input")).getAttribute("value"));
        assertEquals(name, ingredient.findElement(By.tagName("span")).getText());
    }

    private void clickDesignATaco() {
        assertEquals(homePageUrl(), browser.getCurrentUrl());
        browser.findElementByCssSelector("a[id='design']").click();
    }

    private void clickBuildAnotherTaco() {
        assertTrue(browser.getCurrentUrl().startsWith(orderDetailsPageUrl()));
        browser.findElementByCssSelector("a[id='another']").click();
    }

    //
    // URL helper methods
    //
    private String loginPageUrl() {
        return homePageUrl() + "login";
    }

    private String registrationPageUrl() {
        return homePageUrl() + "register";
    }

    private String designPageUrl() {
        return homePageUrl() + "design";
    }

    private String homePageUrl() {
        return "http://localhost:" + port + "/";
    }

    private String orderDetailsPageUrl() {
        return homePageUrl() + "orders";
    }

    private String currentOrderDetailsPageUrl() {
        return homePageUrl() + "orders/current";
    }

}
