package com.sap.bits.api.LeaveScheduler.controller.graphql;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import com.sap.bits.api.LeaveScheduler.dto.request.LoginRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.RegisterRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.PasswordChangeRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;

@Controller
public class AuthGraphQLController {

    @MutationMapping
    @Operation(summary = "Authenticate user and return JWT token")
    public ApiResponse login(@Argument("loginRequest") LoginRequest loginRequest) {
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...");
        tokenData.put("type", "Bearer");
        tokenData.put("expiresIn", 3600);
        return new ApiResponse(true, "Login successful", tokenData);
    }

    @MutationMapping
    public ApiResponse register(@Argument("registerRequest") RegisterRequest registerRequest) {
        return new ApiResponse(true, "User registered successfully");
    }

    @MutationMapping
    public ApiResponse registerBatch(@Argument("registerRequests") List<RegisterRequest> registerRequests) {
        List<Map<String, Object>> users = new ArrayList<>();

        Map<String, Object> user1 = new HashMap<>();
        user1.put("username", "john.doe");
        user1.put("fullName", "John Doe");
        user1.put("email", "john.doe@example.com");
        user1.put("roles", "EMPLOYEE");
        user1.put("department", "Engineering");
        user1.put("phone", "+1-555-0101");
        user1.put("emergencyContact", "Jane Doe: +1-555-0199");

        Map<String, Object> user2 = new HashMap<>();
        user2.put("username", "mary.smith");
        user2.put("fullName", "Mary Smith");
        user2.put("email", "mary.smith@example.com");
        user2.put("roles", "MANAGER");
        user2.put("department", "HR");
        user2.put("phone", "+1-555-0202");
        user2.put("emergencyContact", "Mark Smith: +1-555-0299");

        users.add(user1);
        users.add(user2);

        return new ApiResponse(true, "Users registered successfully (mock)", users);
    }

    @MutationMapping
    public ApiResponse changePassword(@Argument("passwordChangeRequest") PasswordChangeRequest passwordChangeRequest) {
        return new ApiResponse(true, "Password changed successfully");
    }

    @MutationMapping
    public ApiResponse forgotPassword(@Argument String email) {
        return new ApiResponse(true, "Reset password link sent to email");
    }

    @MutationMapping
    public ApiResponse resetPassword(@Argument String token, @Argument String newPassword) {
        return new ApiResponse(true, "Password reset successfully");
    }

    @MutationMapping
    public ApiResponse logout(@Argument String token) {
        return new ApiResponse(true, "User Logged out successfully");
    }
}
