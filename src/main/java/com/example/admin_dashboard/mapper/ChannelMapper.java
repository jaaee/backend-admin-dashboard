package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.response.ChannelResponse;
import com.example.admin_dashboard.model.Channel;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper {
    public ChannelResponse toResponse(Channel channel) {
        return ChannelResponse.builder()
                .id(channel.getId())
                .channelName(channel.getChannelName())
                .build();
    }
}
