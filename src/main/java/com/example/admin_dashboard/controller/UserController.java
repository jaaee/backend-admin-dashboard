package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.service.UserService;
import com.example.admin_dashboard.dto.request.UserRequest;
import com.example.admin_dashboard.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔷 2. Get user by ID
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 🔷 3. Add user
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest user) {
        return userService.createUser(user);
    }

    // 🔷 4. Update user
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,
                           @Valid @RequestBody UserRequest user) {
        return userService.updateUser(id, user);
    }
}
