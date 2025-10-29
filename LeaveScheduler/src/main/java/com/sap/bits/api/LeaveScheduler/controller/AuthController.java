package com.sap.bits.api.LeaveScheduler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bits.api.LeaveScheduler.dto.request.LoginRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.PasswordChangeRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.RegisterRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {


    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT token")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String response = "Login Successful";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/register/batch")
    @Operation(summary = "Register multiple users (mocked)")
    public ResponseEntity<ApiResponse> registerBatch(@Valid @RequestBody List<RegisterRequest> registerRequests) {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> user1 = new HashMap<>();
        user1.put("username", "john.doe");
        user1.put("fullName", "John Doe");
        user1.put("email", "john.doe@example.com");
        user1.put("roles", "EMPLOYEE");
        user1.put("department", "Engineering");
        user1.put("phone", "+1-555-0101");
        user1.put("emergencyContact", "Jane Doe: +1-555-0199");

        Map<String, String> user2 = new HashMap<>();
        user2.put("username", "mary.smith");
        user2.put("fullName", "Mary Smith");
        user2.put("email", "mary.smith@example.com");
        user2.put("roles", "MANAGER");
        user2.put("department", "HR");
        user2.put("phone", "+1-555-0202");
        user2.put("emergencyContact", "Mark Smith: +1-555-0299");

        list.add(user1);
        list.add(user2);
        return ResponseEntity.ok(new ApiResponse(true, "Users registered successfully (mock)", list));
    }

    @PostMapping("/change-password")
    @Operation(summary = "Change user password")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse> changePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
        return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully"));
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Send reset password link to user's email")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(new ApiResponse(true, "Reset password link sent to email"));
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset user password using token")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        return ResponseEntity.ok(new ApiResponse(true, "Password reset successfully"));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Logs out the currently authenticated user")
    public ResponseEntity<ApiResponse> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new ApiResponse(true, "User Logged out successfully"));
    }
}