package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.request.LoginRequest;
import com.example.admin_dashboard.dto.request.UserRequest;
import com.example.admin_dashboard.dto.response.LoginResponse;
import com.example.admin_dashboard.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest request);

    LoginResponse login(LoginRequest request);

      long getActiveUsersCount();
}

