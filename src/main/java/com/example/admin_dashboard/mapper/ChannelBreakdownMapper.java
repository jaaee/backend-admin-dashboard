package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.ChannelBreakdownResponse;
import com.example.admin_dashboard.projection.ChannelBreakdownProjection;
import org.springframework.stereotype.Component;

@Component
public class ChannelBreakdownMapper {

    public ChannelBreakdownResponse toResponse(
            ChannelBreakdownProjection projection,
            double grandTotal) {

        double percentage = grandTotal == 0
                ? 0
                : (projection.getTotalAmount() * 100) / grandTotal;

        return ChannelBreakdownResponse.builder()
                .name(projection.getChannelName())
                .count(projection.getTotalAmount())
                .percent(Math.round(percentage * 100.0) / 100.0)
                .build();
    }
}
