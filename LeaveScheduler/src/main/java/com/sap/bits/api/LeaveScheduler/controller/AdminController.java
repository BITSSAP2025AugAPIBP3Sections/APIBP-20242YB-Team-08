package com.sap.bits.api.LeaveScheduler.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bits.api.LeaveScheduler.dto.request.LeavePolicyRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.UserUpdateRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.DashboardStatsResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import com.sap.bits.api.LeaveScheduler.service.AdminService;
import com.sap.bits.api.LeaveScheduler.service.LeaveCreditService;
import com.sap.bits.api.LeaveScheduler.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Management", description = "Endpoints for administrative operations")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

        @Autowired
        private AdminService adminService;

        @Autowired
        private UserService userService;

        @Autowired
        private LeaveCreditService leaveCreditService;

        @GetMapping("/dashboard-stats")
        @Operation(summary = "Get admin dashboard statistics")
        public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
                DashboardStatsResponse stats = adminService.getDashboardStats();
                return ResponseEntity.ok(stats);
        }

        @GetMapping("/users")
        @Operation(summary = "Get all users")
        public ResponseEntity<List<UserResponse>> getAllUsers() {
                List<UserResponse> users = adminService.getAllUsers();
                return ResponseEntity.ok(users);
        }

        @PutMapping("/users/{userId}")
        @Operation(summary = "Update user details")
        public ResponseEntity<UserResponse> updateUser(
                        @PathVariable Long userId,
                        @Valid @RequestBody UserUpdateRequest request) {
                UserResponse response = adminService.updateUser(userId, request);
                return ResponseEntity.ok(response);
        }

        @PostMapping("/leave-policies")
        @Operation(summary = "Create or update leave policy")
        public ResponseEntity<LeavePolicy> createOrUpdateLeavePolicy(
                        @Valid @RequestBody LeavePolicyRequest request) {
                LeavePolicy policy = adminService.createOrUpdateLeavePolicy(request);
                return ResponseEntity.ok(policy);
        }

        @GetMapping("/leave-policies")
        @Operation(summary = "Get all leave policies")
        public ResponseEntity<List<LeavePolicy>> getAllLeavePolicies() {
                List<LeavePolicy> policies = adminService.getAllLeavePolicies();
                return ResponseEntity.ok(policies);
        }

        @GetMapping("/leave-policies/{id}")
        @Operation(summary = "Get leave policy by ID")
        public ResponseEntity<LeavePolicy> getLeavePolicyById(@PathVariable Long id) {
                LeavePolicy policy = adminService.getLeavePolicyById(id);
                return ResponseEntity.ok(policy);
        }

        @DeleteMapping("/leave-policies/{id}")
        @Operation(summary = "Delete leave policy")
        public ResponseEntity<ApiResponse> deleteLeavePolicy(@PathVariable Long id) {
                ApiResponse response = adminService.deleteLeavePolicy(id);
                return ResponseEntity.ok(response);
        }

        @PostMapping("/credit-leaves")
        @Operation(summary = "Credit annual leaves to all users")
        public ResponseEntity<ApiResponse> creditAnnualLeaveForAllUsers() {
                ApiResponse response = leaveCreditService.creditAnnualLeaveForAllUsers();
                return ResponseEntity.ok(response);
        }

        @PostMapping("/credit-special-leave")
        @Operation(summary = "Credit special leave to specific users")
        public ResponseEntity<List<ApiResponse>> creditSpecialLeave(
                        @RequestParam List<Long> userIds,
                        @RequestParam LeaveType leaveType,
                        @RequestParam float amount,
                        @RequestParam String reason) {
                List<ApiResponse> responses = leaveCreditService.creditSpecialLeave(userIds, leaveType, amount, reason);
                return ResponseEntity.ok(responses);
        }

        @GetMapping("/managed")
        @Operation(summary = "Get users managed by Specific Manager")
        @SecurityRequirement(name = "bearerAuth")
        public ResponseEntity<List<UserResponse>> getManagedUsers(@RequestParam(required = false) Long managerId) {
                List<UserResponse> users = userService.getUsersByManagerId(managerId);
                return ResponseEntity.ok(users);
        }
}