package com.example.admin_dashboard.mapper;

import com.example.admin_dashboard.model.User;
import com.example.admin_dashboard.dto.request.UserRequest;
import com.example.admin_dashboard.dto.response.UserResponse;

public class UserMapper {

    public static User toEntity(UserRequest req) {
        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setCreatedDate(user.getCreatedDate());
        res.setUpdatedDate(user.getUpdatedDate());
        return res;
    }
}