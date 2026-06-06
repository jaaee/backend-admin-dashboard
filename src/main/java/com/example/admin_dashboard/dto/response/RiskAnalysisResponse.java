package com.example.admin_dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskAnalysisResponse {
    private String label;

    private Double percentage;
}
