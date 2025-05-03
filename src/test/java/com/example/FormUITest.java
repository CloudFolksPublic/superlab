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
        String pageUrl = "http://localhost:8081/";
        try {
            driver.get(pageUrl);
            WebElement button = driver.findElement(By.tagName("button"));
            button.click();
            Thread.sleep(1000); // Wait for any potential UI response

            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(
                currentUrl.contains("success") || currentUrl.contains("submitted"),
                "❌ Submit button failed on page: '" + pageUrl + "' — Expected redirect to include 'success' or 'submitted', but got: '" + currentUrl + "'"
            );
        } catch (Throwable t) {
            Assertions.fail("❌ Exception occurred on page: '" + pageUrl + "' — " + t.getMessage(), t);
        }
    }

    @Test
    public void testBrokenFormSubmitButton() {
        String pageUrl = "http://localhost:8081/broken";
        try {
            driver.get(pageUrl);
            WebElement button = driver.findElement(By.tagName("button"));
            button.click();
            Thread.sleep(1000); // Wait for any potential UI response

            String currentUrl = driver.getCurrentUrl();
            Assertions.assertFalse(
                currentUrl.contains("success") || currentUrl.contains("submitted"),
                "❌ Submit button on page: '" + pageUrl + "' unexpectedly redirected to: '" + currentUrl + "'"
            );
        } catch (Throwable t) {
            Assertions.fail("❌ Exception occurred on page: '" + pageUrl + "' — " + t.getMessage(), t);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
