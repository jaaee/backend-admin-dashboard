package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.dto.response.LiveCounterResponse;
import com.example.admin_dashboard.dto.response.RiskAnalysisResponse;
import com.example.admin_dashboard.dto.response.StatisticsResponse;
import com.example.admin_dashboard.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/summary")
    public List<StatisticsResponse> getDashboardStatistics() {

        return statisticsService.getDashboardStatistics();
    }

    @GetMapping("/risk-analysis")
    public List<RiskAnalysisResponse> getRiskAnalysis(){
        return statisticsService.getRiskAnalysis();
    }

    @GetMapping("/live-counters")
    public ResponseEntity<LiveCounterResponse> getLiveCounters() {

        return ResponseEntity.ok(
                statisticsService.getLiveCounters()
        );
    }

    @GetMapping("/totalTransactionTrend")
    public
    ResponseEntity<List<Long>> totalTransactionTrend() {

        return ResponseEntity.ok(
                statisticsService.totalTransactionTrend()
        );
    }

    @GetMapping("/highRisktransactionTrend")
    public
    ResponseEntity<List<Long>> highRisktransactionTrend() {

        return ResponseEntity.ok(
                statisticsService.highRisktransactionTrend()
        );
    }
}