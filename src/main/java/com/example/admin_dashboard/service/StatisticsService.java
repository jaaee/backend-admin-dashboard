package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.response.LiveCounterResponse;
import com.example.admin_dashboard.dto.response.RiskAnalysisResponse;
import com.example.admin_dashboard.dto.response.StatisticsResponse;

import java.util.List;

public interface StatisticsService {
     List<StatisticsResponse> getDashboardStatistics();
     List<RiskAnalysisResponse> getRiskAnalysis();
     LiveCounterResponse getLiveCounters();
     List<Long> totalTransactionTrend();
     List<Long> highRisktransactionTrend();
}
