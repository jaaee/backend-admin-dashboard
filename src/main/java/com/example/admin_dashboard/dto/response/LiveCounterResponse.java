package com.example.admin_dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiveCounterResponse {
    private Long transactionsPerSecond;

    private Long activeUsers;

    private Long pendingQueue;

    private Long fraudAlerts;
}
