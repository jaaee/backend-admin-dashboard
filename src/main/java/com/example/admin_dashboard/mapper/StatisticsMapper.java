package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.StatisticsResponse;
import com.example.admin_dashboard.enums.StatisticsType;
import org.springframework.stereotype.Component;

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
                                         Long value) {
        return switch (type) {

            case TOTAL_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Total Transactions")
                            .value(value)
                            .change("12.5% vs yesterday")
                            .isIncrease(true)
                            .chartData(List.of(
                                            1200,
                                    1400,
                                    1350,
                                    1600,
                                    1800,
                                    1750,
                                    2100,
                                    2300,
                                    2200,
                                    2500,
                                    2400,
                                    2800))
                            .build();

            case PENDING_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Pending Transactions")
                            .value(value)
                            .change("5.6% vs yesterday")
                            .isIncrease(true)
                            .chartData(List.of(
                                    200,
                                    240,
                                    220,
                                    260,
                                    300,
                                    280,
                                    320,
                                    350,
                                    340,
                                    380,
                                    360,
                                    400))
                            .build();

            case FAILED_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Failed Transactions")
                            .value(value)
                            .change("4.3% vs yesterday")
                            .isIncrease(false)
                            .chartData(List.of(
                                    120,
                                    110,
                                    105,
                                    100,
                                    95,
                                    90,
                                    88,
                                    85,
                                    82,
                                    80,
                                    75,
                                    70))
                            .build();

            case HIGH_RISK_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("High Risk Transactions")
                            .value(value)
                            .change("8.2% vs yesterday")
                            .isIncrease(false)
                            .chartData(List.of(
                                    60,
                                    65,
                                    70,
                                    68,
                                    72,
                                    75,
                                    78,
                                    82,
                                    85,
                                    88,
                                    92,
                                    96))
                            .build();

            case SUCCESSFUL_TRANSACTIONS ->
                    StatisticsResponse.builder()
                            .title("Successful Transactions")
                            .value(value)
                            .change("10.8% vs yesterday")
                            .isIncrease(true)
                            .chartData(List.of(
                                    1000,
                                    1200,
                                    1100,
                                    1400,
                                    1600,
                                    1500,
                                    1900,
                                    2000,
                                    1950,
                                    2200,
                                    2150,
                                    2400))
                            .build();
        };
    }
}
