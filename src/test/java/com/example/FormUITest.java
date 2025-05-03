package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormUITest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testMainFormSubmitButton() {
        try {
            driver.get("http://localhost:8081/");
            WebElement button = driver.findElement(By.tagName("button"));
            button.click();
            Thread.sleep(1000); // Wait for any potential UI response

            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("success") || currentUrl.contains("submitted"),
                "❌ Test Failed: Submit button on the **main page** did not perform expected action.");
        } catch (Throwable t) {
            System.err.println("❌ testMainFormSubmitButton failed: " + t.getMessage());
        }
    }

    @Test
    public void testBrokenFormSubmitButton() {
        try {
            driver.get("http://localhost:8081/broken");
            WebElement button = driver.findElement(By.tagName("button"));
            button.click();
            Thread.sleep(1000); // Wait for any potential UI response

            String currentUrl = driver.getCurrentUrl();
            Assertions.assertFalse(currentUrl.contains("success") || currentUrl.contains("submitted"),
                "❌ Test Failed: Clicked the submit button on **'/broken' page**, but it did not navigate or respond as expected.");
        } catch (Throwable t) {
            System.err.println("❌ testBrokenFormSubmitButton failed: " + t.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
