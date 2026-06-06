package com.example.admin_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "channels")
@Data
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channelName;
}
