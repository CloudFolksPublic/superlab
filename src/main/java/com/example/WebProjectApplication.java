package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebProjectApplicationTests {

    @Test
    void contextLoads() {
        // Verify that the Spring context loads successfully
        WebProjectApplication app = new WebProjectApplication();
        assertThat(app).isNotNull();
    }

    @Test
    void homePageReturnsContent() {
        // This test covers the home() method
        WebProjectApplication app = new WebProjectApplication();
        String response = app.home();
        assertThat(response).contains("CloudFolks HUB");
    }
}
