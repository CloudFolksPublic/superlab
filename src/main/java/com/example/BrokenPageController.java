package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokenPageController {

    @GetMapping("/broken")
    public String brokenPage() {
        return "<html>" +
                "<head>" +
                "    <title>CloudFolks HUB - Broken Page</title>" +
                "</head>" +
                "<body style='text-align:center; background-color:#fff8f0;'>" +
                "    <h1 style='color: #FF5733;'>Welcome to <span style='color: #900C3F;'>Broken Page</span>!</h1>" +
                "    <p style='font-size:20px; color: #555;'>This looks like a working form, but the button is broken.</p>" +
                "    <form id='form' method='post' action='/submit' style='margin-top:20px;'>" +
                "        <label for='name'>Name:</label><br>" +
                "        <input type='text' id='name' name='name' required><br><br>" +
                "        <label for='email'>Email:</label><br>" +
                "        <input type='email' id='email' name='email' required><br><br>" +
                "        <!-- This button is disabled or broken -->" +
                "        <button type='submit' onclick='return false;'>Submit</button>" +
                "    </form>" +
                "</body>" +
                "</html>";
    }
}
