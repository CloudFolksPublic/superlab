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
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testMainFormSubmitButton() {
        driver.get("http://localhost:8081/");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        
        // Check that navigation or action occurred
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Submit button on the main page did not perform expected action.");
    }

    @Test
    public void testBrokenFormSubmitButton() {
        driver.get("http://localhost:8081/broken");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        try {
            Thread.sleep(1000); // Wait briefly to see if anything changes
        } catch (InterruptedException e) {
            // Ignored for brevity
        }

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertFalse(currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Clicked the submit button on '/broken' page, but it unexpectedly worked (navigated). It should be broken.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
