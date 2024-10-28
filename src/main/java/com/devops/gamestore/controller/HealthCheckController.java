package com.devops.gamestore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling health check requests.
 */
@RestController
public class HealthCheckController {

    /**
     * Endpoint for checking the health of the application.
     *
     * @return a string indicating the health status
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}