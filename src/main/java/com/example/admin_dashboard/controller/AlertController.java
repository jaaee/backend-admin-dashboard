package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.dto.response.AlertResponse;
import com.example.admin_dashboard.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    AlertService alertService;

    @GetMapping
    public List<AlertResponse> getAllAlerts() {

        return alertService.getAllAlerts();
    }
}
