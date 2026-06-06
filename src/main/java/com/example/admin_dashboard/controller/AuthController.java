package com.example.admin_dashboard.controller;

import com.example.admin_dashboard.dto.request.LoginRequest;
import com.example.admin_dashboard.dto.response.LoginResponse;
import com.example.admin_dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }

//    // Send OTP
//    @PostMapping("/send-otp")
//    public ResponseEntity<String> sendOtp(
//            @RequestParam String mobileNumber) {
//
//        // generate otp
//        String otp = "5678";
//
//        // send otp logic here
//
//        return ResponseEntity.ok("OTP Sent Successfully");
//    }
//
//    // Verify OTP
//    @PostMapping("/verify-otp")
//    public ResponseEntity<String> verifyOtp(
//            @RequestParam String mobileNumber,
//            @RequestParam String otp) {
//
//        // dummy otp validation
//        if ("5678".equals(otp)) {
//            return ResponseEntity.ok("OTP Verified Successfully");
//        }
//
//        return ResponseEntity.badRequest().body("Invalid OTP");
//    }


}
