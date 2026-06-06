package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.response.ChannelResponse;
import com.example.admin_dashboard.mapper.ChannelMapper;
import com.example.admin_dashboard.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements  ChannelService{

    @Autowired
     ChannelRepository channelRepository;

    @Autowired
     ChannelMapper channelMapper;

    @Override
    public List<ChannelResponse> getAllChannels(){
        return channelRepository.findAll()
                .stream()
                .map(channelMapper::toResponse)
                .toList();
    }

}
