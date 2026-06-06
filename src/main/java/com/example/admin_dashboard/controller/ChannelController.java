package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.dto.response.ChannelResponse;
import com.example.admin_dashboard.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @GetMapping
    public List<ChannelResponse> getAllChannels() {

        return channelService.getAllChannels();
    }
}
