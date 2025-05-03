package com.example;

public class ErrorGenerator {

    public void riskyMethod() {
        try {
            int result = 10 / 0; // Division by zero - known bad practice
        } catch (Exception e) {
            // Swallowing exception without logging or handling
        }
    }
}
