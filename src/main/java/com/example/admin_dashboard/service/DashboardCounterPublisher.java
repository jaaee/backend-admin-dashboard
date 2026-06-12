package com.example.admin_dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardCounterPublisher {

    private final SimpMessagingTemplate messagingTemplate;
    private final StatisticsService statisticsService;

    @Scheduled(fixedRate = 1000000)
    public void publishLiveCounters() {



        messagingTemplate.convertAndSend(
                "/topic/live-counters",
                statisticsService.getLiveCounters()
        );
    }
}