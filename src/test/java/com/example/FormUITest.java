package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormUITest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Auto-setup ChromeDriver
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFormOnMainPage() {
        driver.get("http://localhost:8081/");

        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

            nameField.sendKeys("Leo");
            emailField.sendKeys("leo@example.com");
            submitButton.click();

            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            assertTrue(message.getText().contains("Thank You"),
                    "✅ Main page form submission should display 'Thank You' message.");

        } catch (Exception e) {
            fail("❌ Test Failed on MAIN page ('/'): " + e.getMessage());
        }
    }

    @Test
    public void testFormOnBrokenPage() {
        driver.get("http://localhost:8081/broken");

        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

            nameField.sendKeys("Ray");
            emailField.sendKeys("ray@example.com");
            submitButton.click();

            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            assertTrue(message.getText().toLowerCase().contains("error") || message.getText().toLowerCase().contains("invalid"),
                    "⚠️ Broken page should show an error or invalid message.");

        } catch (Exception e) {
            fail("❌ Test Failed on BROKEN page ('/broken'): " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
