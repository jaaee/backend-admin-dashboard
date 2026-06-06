package com.example.admin_dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChannelBreakdownResponse {
    private String name;
    private Double count;
    private Double percent;
}
