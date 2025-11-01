package com.sap.bits.api.LeaveScheduler.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
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
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Management", description = "Endpoints for administrative operations")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    @GetMapping("/stats")
    @Operation(summary = "Get admin dashboard statistics")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        Map<UserRole, Long> roleDist = new java.util.EnumMap<>(UserRole.class);
        roleDist.put(UserRole.ADMIN, 2L);
        roleDist.put(UserRole.MANAGER, 5L);
        roleDist.put(UserRole.EMPLOYEE, 28L);
        DashboardStatsResponse dummy = new DashboardStatsResponse(
                35L,
                33L,
                4L,
                roleDist,
                new ArrayList<>());
        return ResponseEntity.ok(dummy);
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        UserResponse u1 = new UserResponse(1L, "john.doe", "John Doe", "john.doe@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", 3L, "Mary Smith", LocalDate.of(2023, 1, 15), "+1-555-0101",
                "Jane Doe: +1-555-0199", true, LocalDateTime.now().minusHours(2));
        UserResponse u2 = new UserResponse(2L, "mary.smith", "Mary Smith", "mary.smith@example.com",
                new java.util.HashSet<>(Arrays.asList(UserRole.MANAGER)),
                "HR", null, null, LocalDate.of(2022, 6, 1), "+1-555-0202",
                "Mark Smith: +1-555-0299", true, LocalDateTime.now().minusMinutes(30));
        return ResponseEntity.ok(Arrays.asList(u1, u2));
    }

    @PutMapping("/users/{userId}")
    @Operation(summary = "Update user details")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request) {
        UserResponse updated = new UserResponse(
                userId,
                "updated.user",
                request.getFullName() != null ? request.getFullName() : "Updated User",
                request.getEmail() != null ? request.getEmail() : "updated.user@example.com",
                request.getRoles() != null ? request.getRoles()
                        : new HashSet<>(
                                Arrays.asList(UserRole.EMPLOYEE)),
                request.getDepartment() != null ? request.getDepartment() : "Operations",
                request.getManagerId(),
                null,
                null,
                null,
                null,
                request.getIsActive() != null && request.getIsActive(),
                LocalDateTime.now());
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/leave-policies")
    @Operation(summary = "Create or update leave policy")
    public ResponseEntity<LeavePolicy> createOrUpdateLeavePolicy(
            @Valid @RequestBody LeavePolicyRequest request) {
        LeavePolicy lp = new LeavePolicy(
                100L,
                request.getLeaveType(),
                request.getDescription() != null ? request.getDescription() : "Sample leave policy",
                request.getAnnualCredit() != null ? request.getAnnualCredit() : 12.0f,
                request.getMaxAccumulation(),
                request.getIsCarryForward(),
                request.getMinDuration(),
                request.getMaxDuration(),
                request.getNoticeRequired(),
                request.getApplicableRoles() != null ? request.getApplicableRoles()
                        : new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                request.getIsActive(),
                LocalDateTime.now(),
                LocalDateTime.now());
        return ResponseEntity.ok(lp);
    }

    @GetMapping("/leave-policies")
    @Operation(summary = "Get all leave policies")
    public ResponseEntity<List<LeavePolicy>> getAllLeavePolicies() {
        LeavePolicy p1 = new LeavePolicy(
                101L,
                LeaveType.CASUAL,
                "Casual leave for personal reasons",
                12.0f,
                24.0f,
                true,
                1,
                5,
                1,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                true,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(2));
        LeavePolicy p2 = new LeavePolicy(
                102L,
                LeaveType.SICK,
                "Sick leave for health issues",
                10.0f,
                20.0f,
                true,
                1,
                7,
                0,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE, UserRole.MANAGER)),
                true,
                LocalDateTime.now().minusDays(20),
                LocalDateTime.now().minusDays(5));
        return ResponseEntity.ok(Arrays.asList(p1, p2));
    }

    @GetMapping("/leave-policies/{id}")
    @Operation(summary = "Get leave policy by ID")
    public ResponseEntity<LeavePolicy> getLeavePolicyById(@PathVariable Long id) {
        LeavePolicy single = new LeavePolicy(
                id,
                LeaveType.EARNED,
                "Earned leave sample policy",
                15.0f,
                30.0f,
                true,
                1,
                10,
                2,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE, UserRole.MANAGER)),
                true,
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now());
        return ResponseEntity.ok(single);
    }

    @DeleteMapping("/leave-policies/{id}")
    @Operation(summary = "Delete leave policy")
    public ResponseEntity<ApiResponse> deleteLeavePolicy(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(true, "Policy deleted (mock)", Map.of("deletedId", id)));
    }

    @PostMapping("/leaves/credit")
    @Operation(summary = "Credit annual leaves to all users")
    public ResponseEntity<ApiResponse> creditAnnualLeaveForAllUsers() {
        return ResponseEntity
                .ok(new ApiResponse(true, "Annual leave credited to all users (mock)", Map.of("credited", true)));
    }

    @PostMapping("/leaves/credit-special")
    @Operation(summary = "Credit special leave to specific users")
    public ResponseEntity<List<ApiResponse>> creditSpecialLeave(
            @RequestParam List<Long> userIds,
            @RequestParam LeaveType leaveType,
            @RequestParam float amount,
            @RequestParam String reason) {
        List<ApiResponse> results = new ArrayList<>();
        for (Long uid : userIds) {
            results.add(new ApiResponse(true, "Special leave credited (mock)", Map.of(
                    "userId", uid,
                    "leaveType", leaveType.name(),
                    "amount", amount,
                    "reason", reason)));
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/managed")
    @Operation(summary = "Get users managed by Specific Manager")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserResponse>> getManagedUsers(@RequestParam(required = false) Long managerId) {
        UserResponse managed1 = new UserResponse(10L, "alice", "Alice Johnson", "alice@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", managerId != null ? managerId : 2L, null, LocalDate.of(2023, 3, 10),
                "+1-555-0303",
                "Bob Johnson: +1-555-0399", true, LocalDateTime.now().minusHours(5));
        UserResponse managed2 = new UserResponse(11L, "bob", "Bob Martin", "bob@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", managerId != null ? managerId : 2L, null, LocalDate.of(2023, 4, 1),
                "+1-555-0404",
                "Alice Johnson: +1-555-0499", true, LocalDateTime.now().minusHours(1));
        return ResponseEntity.ok(Arrays.asList(managed1, managed2));
    }
}