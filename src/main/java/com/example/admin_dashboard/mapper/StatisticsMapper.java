package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.StatisticsResponse;
import com.example.admin_dashboard.enums.StatisticsType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class StatisticsMapper {
//    public StatisticsResponse toResponse(
//            long totalTransactions,
//            long pendingTransactions,
//            long failedTransactions,
//            long successfulTransactions,
//            long highRiskTransactions) {
//
//        return StatisticsResponse.builder()
//                .totalTransactions(totalTransactions)
//                .pendingTransactions(pendingTransactions)
//                .successfulTransactions(successfulTransactions)
//                .failedTransactions(failedTransactions)
//                .highRiskTransactions(highRiskTransactions)
//                .build();
//    }

    public StatisticsResponse toResponse(StatisticsType type,
                                         Long value, BigDecimal change, List<Long> chartData) {
        Boolean  isIncrease = change.compareTo(BigDecimal.ZERO) >= 0;
        return switch (type) {

            case TOTAL_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Total Transactions")
                            .value(value)
                            .change( change.abs() + " vs yesterday")
                            .isIncrease(isIncrease)
                            .chartData(chartData)
                            .build();

            case PENDING_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Pending Transactions")
                            .value(value)
                            .change(change.abs() + " vs yesterday")
                            .isIncrease(isIncrease)
                            .chartData(chartData)
                            .build();

            case FAILED_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Failed Transactions")
                            .value(value)
                            .change(change.abs() + " vs yesterday")
                            .isIncrease(isIncrease)
                            .chartData(chartData)
                            .build();

            case HIGH_RISK_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("High Risk Transactions")
                            .value(value)
                            .change(change.abs() + " vs yesterday")
                            .isIncrease(isIncrease)
                            .chartData(chartData)
                            .build();

            case SUCCESSFUL_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Successful Transactions")
                            .value(value)
                            .change(change.abs() + " vs yesterday")
                            .isIncrease(isIncrease)
                            .chartData(chartData)
                            .build();
        };
    }
}
