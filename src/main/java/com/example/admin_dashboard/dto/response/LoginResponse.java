package com.example.admin_dashboard.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String userName;
    private String email;
    private String role;
}
