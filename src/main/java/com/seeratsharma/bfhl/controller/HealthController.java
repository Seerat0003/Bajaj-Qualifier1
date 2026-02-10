package com.seeratsharma.bfhl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("official_email", "seerat4811.be23@chitkara.edu.in");
        return response;
    }
}
