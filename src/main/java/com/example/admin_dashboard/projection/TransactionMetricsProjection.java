package com.example.admin_dashboard.projection;

import java.math.BigDecimal;

public interface TransactionMetricsProjection {
    BigDecimal getTotalChangePct();

    BigDecimal getSuccessChangePct();

    BigDecimal getFailedChangePct();

    BigDecimal getPendingChangePct();

    BigDecimal getHighRiskChangePct();
}
