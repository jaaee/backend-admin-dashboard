package com.example.admin_dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChannelResponse {
    private Long id;

    private String channelName;
}
