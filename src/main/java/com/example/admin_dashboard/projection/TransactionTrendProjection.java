package com.example.admin_dashboard.projection;

public interface TransactionTrendProjection {
   Long getTotalTransactions();

    Long getTotalPending();

    Long getTotalSuccess();

    Long getTotalFailed();

    Long getTotalHighRisk();
}
