package com.devops.gamestore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for health check endpoints.
 */
@RestController
public class HealthCheckController {

    /**
     * Health check endpoint.
     *
     * @return a message indicating the service is up
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is up and running";
    }

}