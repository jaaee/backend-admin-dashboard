package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.response.AlertResponse;
import com.example.admin_dashboard.mapper.AlertMapper;
import com.example.admin_dashboard.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;
    @Autowired
    AlertMapper alertMapper;

    @Override
   public List<AlertResponse> getAllAlerts(){
        return alertRepository
                .findAllByOrderByCreatedDateDesc()
                .stream()
                .map(alertMapper::toResponse)
                .toList();
    }

    @Override
    public long getOpenFraudAlertsCount() {

        return alertRepository.countFraudAlerts(

        );
    }
}
