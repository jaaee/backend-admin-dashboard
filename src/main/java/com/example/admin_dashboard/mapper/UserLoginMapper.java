package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.dto.request.LoginRequest;
import com.example.admin_dashboard.dto.request.UserRequest;
import com.example.admin_dashboard.dto.response.LoginResponse;
import com.example.admin_dashboard.dto.response.UserResponse;
import com.example.admin_dashboard.model.User;

public class UserLoginMapper {



    public static LoginResponse toResponse(User user) {
        LoginResponse res = new LoginResponse();
        res.setId(user.getId());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setUserName(user.getUserName());
        res.setMobileNumber(user.getMobileNumber());
        res.setRole(user.getRole());
        res.setEmail(user.getEmail());

        return res;
    }
}
