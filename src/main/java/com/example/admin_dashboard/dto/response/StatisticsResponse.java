package com.example.admin_dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatisticsResponse {
//    private Long totalTransactions;
//
//    private Long successfulTransactions;
//
//    private Long pendingTransactions;
//
//    private Long failedTransactions;
//
//    private Long highRiskTransactions;

    private String title;
    private Long value;
    private String change;
    private Boolean isIncrease;
    private List<Long> chartData;
}
