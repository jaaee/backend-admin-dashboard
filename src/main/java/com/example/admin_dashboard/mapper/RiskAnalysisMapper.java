package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.RiskAnalysisResponse;
import com.example.admin_dashboard.enums.RiskLevel;
import com.example.admin_dashboard.projection.RiskAnalysisProjection;
import org.springframework.stereotype.Component;

@Component
public class RiskAnalysisMapper {public RiskAnalysisResponse toResponse(
        RiskAnalysisProjection projection,
        long totalTransactions) {

    double percentage = totalTransactions == 0
            ? 0
            : (projection.getCount() * 100.0) / totalTransactions;

    return RiskAnalysisResponse.builder()
            .label(formatRiskLevel(projection.getRiskLevel()))
            .percentage(
                    Math.round(percentage * 100.0) / 100.0)
            .build();
}

    private String formatRiskLevel(RiskLevel riskLevel) {

        return switch (riskLevel) {
            case LOW -> "Low Risk";
            case MEDIUM -> "Medium Risk";
            case HIGH -> "High Risk";
            case CRITICAL -> "Critical Risk";
        };
    }
}
