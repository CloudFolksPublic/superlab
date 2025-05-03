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
        System.out.println("🔍 Testing submit button on the MAIN page ('/')");

        driver.get("http://localhost:8081/");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();

        try {
            Thread.sleep(1000); // Wait for any UI response
        } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(
            currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Submit button on **MAIN page ('/')** did not navigate or respond as expected."
        );
    }

    @Test
    public void testBrokenFormSubmitButton() {
        System.out.println("🔍 Testing submit button on the BROKEN page ('/broken')");

        driver.get("http://localhost:8081/broken");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();

        try {
            Thread.sleep(1000); // Wait for any UI response
        } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertFalse(
            currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Submit button on **BROKEN page ('/broken')** unexpectedly navigated or responded."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
