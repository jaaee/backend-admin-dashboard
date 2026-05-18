package com.example.admin_dashboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be empty")
    private String firstName;


    private String lastName;

}
