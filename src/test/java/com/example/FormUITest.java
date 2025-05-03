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
    public void testMainFormSubmitButton() throws InterruptedException {
        driver.get("http://localhost:8081/");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        Thread.sleep(1000); // Wait for any potential UI response

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(
            currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Submit button on the **main page** (FormUITest.java) did not perform expected action."
        );
    }

    @Test
    public void testBrokenFormSubmitButton() throws InterruptedException {
        driver.get("http://localhost:8081/broken");
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        Thread.sleep(1000); // Wait for any potential UI response

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertFalse(
            currentUrl.contains("success") || currentUrl.contains("submitted"),
            "❌ Test Failed: Clicked the submit button on the **'/broken' page** (FormUITest.java), but it unexpectedly redirected."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
