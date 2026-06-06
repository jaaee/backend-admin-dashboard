package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.response.AlertResponse;

import java.util.List;

public interface AlertService {
    List<AlertResponse> getAllAlerts();
    long  getOpenFraudAlertsCount();
}
