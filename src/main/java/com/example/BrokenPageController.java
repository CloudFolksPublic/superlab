package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokenPageController {

    @GetMapping("/broken")
    public String brokenPage() {
        return "<html>" +
                "<head><title>Broken Page</title></head>" +
                "<body style='text-align:center; background-color:#ffcccc;'>" +
                "    <h1 style='color: red;'>This page is under construction</h1>" +
                "    <p>Error: Button below does nothing. Form is missing.</p>" +
                "    <button id='brokenButton'>Click Me</button>" +
                "</body>" +
                "</html>";
    }
}

