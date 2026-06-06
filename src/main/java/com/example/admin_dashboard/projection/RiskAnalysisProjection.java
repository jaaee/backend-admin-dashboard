package com.example.admin_dashboard.projection;

import com.example.admin_dashboard.enums.RiskLevel;

public interface RiskAnalysisProjection {

    RiskLevel getRiskLevel();

    Long getCount();
}
