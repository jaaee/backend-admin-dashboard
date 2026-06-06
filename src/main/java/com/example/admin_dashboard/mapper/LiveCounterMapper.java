package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.LiveCounterResponse;
import org.springframework.stereotype.Component;

@Component
public class LiveCounterMapper {
    public LiveCounterResponse toLiveCounterResponse(
            Long transactionsPerSecond,
            Long activeUsers,
            Long pendingQueue,
            Long fraudAlerts
    ) {

        return LiveCounterResponse.builder()
                .transactionsPerSecond(transactionsPerSecond)
                .activeUsers(activeUsers)
                .pendingQueue(pendingQueue)
                .fraudAlerts(fraudAlerts)
                .build();
    }
}
