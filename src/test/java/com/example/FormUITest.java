package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormUITest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Automatically handles driver setup

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testMainFormSubmitButton() {
        driver.get("http://localhost:8081/");
        WebElement button = driver.findElement(By.tagName("button"));
        Assertions.assertTrue(button.isEnabled(), "Main page submit button should be enabled");
    }

    @Test
    public void testBrokenFormSubmitButton() {
        driver.get("http://localhost:8081/broken");
        WebElement button = driver.findElement(By.tagName("button"));
        Assertions.assertFalse(button.isEnabled(), "Broken page submit button should be disabled");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
