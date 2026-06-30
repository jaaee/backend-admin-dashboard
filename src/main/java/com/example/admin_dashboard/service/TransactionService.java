package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.request.TransactionFilterRequest;
import com.example.admin_dashboard.dto.response.ChannelBreakdownResponse;
import com.example.admin_dashboard.dto.response.PagedResponse;
import com.example.admin_dashboard.dto.response.TransactionResponse;

import com.example.admin_dashboard.projection.RiskAnalysisProjection;
import com.example.admin_dashboard.projection.TransactionMetricsProjection;
import com.example.admin_dashboard.projection.TransactionTrendProjection;


import java.util.List;

public interface TransactionService {
     List<TransactionResponse> getAllTransactions();
    PagedResponse<TransactionResponse>getRecentTransactions(int page,int size);
     List<ChannelBreakdownResponse> getChannelBreakdown() ;
     long getTotalTransactions();

     long getPendingTransactions();

     long getFailedTransactions();

     long getSuccessfulTransactions();

     long getHighRiskTransactions();

     List<RiskAnalysisProjection> getRiskAnalysis();

     PagedResponse<TransactionResponse>
    getFilteredTransactions(TransactionFilterRequest request);

    byte[] exportTransactions(
            TransactionFilterRequest request);

     long getTransactionsPerSecond();
     long getPendingTransactionsCount();
    TransactionMetricsProjection getChangeInTransactions();
    List<TransactionTrendProjection> getTransactionTrends();
}

