package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.request.TransactionFilterRequest;
import com.example.admin_dashboard.dto.response.ChannelBreakdownResponse;
import com.example.admin_dashboard.dto.response.PagedResponse;
import com.example.admin_dashboard.dto.response.TransactionResponse;

import com.example.admin_dashboard.projection.RiskAnalysisProjection;


import java.util.List;

public interface TransactionService {
     List<TransactionResponse> getAllTransactions();
     List<TransactionResponse> getRecentTransactions();
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
}

