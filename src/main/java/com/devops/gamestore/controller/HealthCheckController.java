package com.devops.gamestore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for health check in the Game Store application.
 */
@RestController
public final class HealthCheckController {

    /**
     * Health check endpoint.
     *
     * @return a string indicating the health status
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}