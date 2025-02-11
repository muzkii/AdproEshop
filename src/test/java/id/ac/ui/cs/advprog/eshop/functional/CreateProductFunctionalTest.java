package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_successfullyAddsProduct(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        // Locate form elements
        WebElement productNameInput = driver.findElement(By.name("productName"));
        WebElement productQuantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        // Fill the form
        productNameInput.sendKeys("Test Product");
        productQuantityInput.sendKeys("50");
        submitButton.click();

        // Verify redirection to product list
        driver.get(baseUrl + "/product/list");

        // Check if the new product exists in the list
        boolean isProductAdded = driver.findElements(By.xpath("//td[contains(text(),'Test Product')]")).size() > 0;
        assertTrue(isProductAdded, "Newly created product should be listed.");
    }

    @Test
    void createProduct_withEmptyFields_showsError(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        // Check if error messages exist
        boolean isErrorDisplayed = driver.getPageSource().contains("This field is required");
        assertTrue(isErrorDisplayed, "Validation error should be displayed for empty fields.");
    }

    @Test
    void createProductPage_hasRequiredFields(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        assertNotNull(driver.findElement(By.id("nameInput")), "Name input should be present.");
        assertNotNull(driver.findElement(By.id("quantityInput")), "Quantity input should be present.");
        assertNotNull(driver.findElement(By.tagName("button")), "Submit button should be present.");
    }
}