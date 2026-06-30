package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.response.LiveCounterResponse;
import com.example.admin_dashboard.dto.response.RiskAnalysisResponse;
import com.example.admin_dashboard.dto.response.StatisticsResponse;
import com.example.admin_dashboard.enums.StatisticsType;
import com.example.admin_dashboard.mapper.LiveCounterMapper;
import com.example.admin_dashboard.mapper.RiskAnalysisMapper;
import com.example.admin_dashboard.mapper.StatisticsMapper;
import com.example.admin_dashboard.projection.TransactionMetricsProjection;
import com.example.admin_dashboard.projection.TransactionTrendProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StatisticsServiceImpl implements  StatisticsService {
    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Autowired
    AlertService alertService;

    @Autowired
    StatisticsMapper statisticsMapper;

    @Autowired
    RiskAnalysisMapper riskAnalysisMapper;

    @Autowired
    LiveCounterMapper livecounterMapper;

    @Override
    public List<StatisticsResponse> getDashboardStatistics() {

        long totalTransactions =
                transactionService.getTotalTransactions();

        long pendingTransactions =
                transactionService.getPendingTransactions();

        long successfulTransactions =
                transactionService.getSuccessfulTransactions();

        long failedTransactions =
                transactionService.getFailedTransactions();

        long highRiskTransactions =
                transactionService.getHighRiskTransactions();

        TransactionMetricsProjection changeInTransactions =
                transactionService.getChangeInTransactions();

        List<TransactionTrendProjection> transactionTrends =
                transactionService.getTransactionTrends();

        List<Long> totalTransactionsTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalTransactions)
                .toList();

        List<Long> totalPendingTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalPending)
                .toList();

        List<Long> totalSuccessTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalSuccess)
                .toList();

        List<Long> totalFailedTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalFailed)
                .toList();

        List<Long> totalHighRiskTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalHighRisk)
                .toList();

        return List.of(
                statisticsMapper.toResponse(
                        StatisticsType.TOTAL_TRANSACTIONS,
                        totalTransactions,changeInTransactions.getTotalChangePct(),totalTransactionsTrend),

                statisticsMapper.toResponse(
                        StatisticsType.PENDING_TRANSACTIONS,
                        pendingTransactions,changeInTransactions.getPendingChangePct(),totalPendingTrend),

                statisticsMapper.toResponse(
StatisticsType.SUCCESSFUL_TRANSACTIONS,
                        successfulTransactions,changeInTransactions.getSuccessChangePct(),totalSuccessTrend),

                statisticsMapper.toResponse(
                        StatisticsType.FAILED_TRANSACTIONS,
                        failedTransactions,changeInTransactions.getFailedChangePct(),totalFailedTrend),

                statisticsMapper.toResponse(
StatisticsType.HIGH_RISK_TRANSACTIONS,
                        highRiskTransactions,changeInTransactions.getHighRiskChangePct(),totalHighRiskTrend)
        );

//        return statisticsMapper.toResponse(
//                totalTransactions,
//                pendingTransactions,
//                failedTransactions,
//                successfulTransactions,
//                highRiskTransactions
//        );

    }

    @Override
    public List<RiskAnalysisResponse> getRiskAnalysis() {

        long totalTransactions =
                transactionService.getTotalTransactions();

        return transactionService.getRiskAnalysis()
                .stream()
                .map(item ->
                        riskAnalysisMapper.toResponse(
                                item,
                                totalTransactions))
                .toList();
    }

//                riskAnalysisMapper.toResponse(
//                        transactionService.getRiskLevelCount(
//                                RiskLevel.LOW),
//                        totalTransactions),
//
//                riskAnalysisMapper.toResponse(
//                        "Medium Risk",
//                        transactionService.getRiskLevelCount(
//                                RiskLevel.MEDIUM),
//                        totalTransactions),
//
//                riskAnalysisMapper.toResponse(
//                        "High Risk",
//                        transactionService.getRiskLevelCount(
//                                RiskLevel.HIGH),
//                        totalTransactions),
//
//                riskAnalysisMapper.toResponse(
//                        "Critical Risk",
//                        transactionService.getRiskLevelCount(
//                                RiskLevel.CRITICAL),
//                        totalTransactions)
//        );
  //  }
@Override
    public LiveCounterResponse getLiveCounters(){
    Long transactionsPerSecond =
            transactionService.getTransactionsPerSecond();

    Long activeUsers =
            userService.getActiveUsersCount();

    Long pendingQueue =
            transactionService.getPendingTransactionsCount();

    Long fraudAlerts =
            alertService.getOpenFraudAlertsCount();

    return livecounterMapper.toLiveCounterResponse(
            transactionsPerSecond,
            activeUsers,
            pendingQueue,
            fraudAlerts
    );
}

@Override
public  List<Long> totalTransactionTrend(){

    List<TransactionTrendProjection> transactionTrends =
            transactionService.getTransactionTrends();

    List<Long> totalTransactionsTrend = transactionTrends.stream()
            .map(TransactionTrendProjection::getTotalTransactions)
            .toList();

    return totalTransactionsTrend;
}


   public  List<Long> highRisktransactionTrend(){

        List<TransactionTrendProjection> transactionTrends =
                transactionService.getTransactionTrends();

        List<Long> highRisktransactionTrend = transactionTrends.stream()
                .map(TransactionTrendProjection::getTotalHighRisk)
                .toList();

        return highRisktransactionTrend;

    }



}
