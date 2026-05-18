package com.example.admin_dashboard.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
