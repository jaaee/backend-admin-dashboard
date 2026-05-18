package com.example.admin_dashboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "User Name cannot be empty")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    private String password;

}
