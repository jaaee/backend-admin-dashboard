package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.AlertResponse;
import com.example.admin_dashboard.model.Alert;
import org.springframework.stereotype.Component;

@Component
public class AlertMapper {
    public AlertResponse toResponse(Alert alert) {
        return AlertResponse.builder()
                .id(alert.getId())
                .alertCode(alert.getAlertCode())
                .title(alert.getTitle())
                .message(alert.getMessage())
                .type(alert.getType())
                .severity(alert.getSeverity())
                .source(alert.getSource())
                .createdDate(alert.getCreatedDate())
                .transactionRefNo(
                        alert.getTransaction() != null
                                ? alert.getTransaction().getReferenceNo()
                                : null
                )
                .build();
    }

}