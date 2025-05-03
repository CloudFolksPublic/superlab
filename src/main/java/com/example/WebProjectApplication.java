package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class WebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<html>" +
                "<head><title>CloudFolks HUB</title></head>" +
                "<body style='text-align:center; background-color:#f0f8ff;'>" +
                "<h1 style='color: #4CAF50;'>Welcome to <span style='color: #00008B;'>CloudFolks HUB</span>!</h1>" +
                "<p style='font-size:20px; color: #555;'>Empowering Your DevOps Journey</p>" +
                "<form id='form' method='post' action='/submit' style='margin-top:20px;'>" +
                "<label for='name'>Name:</label><br>" +
                "<input type='text' id='name' name='name' required><br><br>" +
                "<label for='email'>Email:</label><br>" +
                "<input type='email' id='email' name='email' required><br><br>" +
                "<button type='submit'>Submit</button>" +
                "</form>" +
                "</body></html>";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String name, @RequestParam String email) {
        return "<html><head><title>Form Submitted</title></head>" +
                "<body style='text-align:center; background-color:#f0f8ff;'>" +
                "<h1 style='color: #4CAF50;'>Thank You, " + name + "!</h1>" +
                "<p style='font-size:20px; color: #555;'>Your email (" + email + ") has been submitted successfully.</p>" +
                "</body></html>";
    }
}
