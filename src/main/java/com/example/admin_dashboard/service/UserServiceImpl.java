package com.example.admin_dashboard.service;

import com.example.admin_dashboard.dto.request.LoginRequest;
import com.example.admin_dashboard.dto.response.LoginResponse;
import com.example.admin_dashboard.exception.AuthenticationException;
import com.example.admin_dashboard.mapper.UserLoginMapper;
import com.example.admin_dashboard.mapper.UserMapper;
import com.example.admin_dashboard.model.User;
import com.example.admin_dashboard.repository.UserRepository;
import com.example.admin_dashboard.dto.request.UserRequest;
import com.example.admin_dashboard.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;


    @Override
    public UserResponse createUser(UserRequest request) {
        User user = UserMapper.toEntity(request);
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUpdatedDate(LocalDateTime.now());

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public LoginResponse login(LoginRequest request) {


        Optional<User> optionalUser =
                userRepository.findByUserNameAndPassword(
                        request.getUserName(),
                        request.getPassword()
                );

        if (optionalUser.isEmpty()) {
            throw new AuthenticationException("Invalid username or password");
        }

        User user = optionalUser.get();

        return UserLoginMapper.toResponse(user);
    }


    @Override
    public long getActiveUsersCount() {

        return userRepository.countActiveUsers(

        );
    }
    }


