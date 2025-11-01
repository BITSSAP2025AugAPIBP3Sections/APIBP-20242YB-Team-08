package com.sap.bits.api.LeaveScheduler.controller.graphql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sap.bits.api.LeaveScheduler.dto.request.LeavePolicyRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.UserUpdateRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.DashboardStatsResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

@Controller
public class AdminGraphQLController {

    // Query Resolvers
    @QueryMapping
    public DashboardStatsResponse getDashboardStats() {
        Map<UserRole, Long> roleDist = new java.util.EnumMap<>(UserRole.class);
        roleDist.put(UserRole.ADMIN, 2L);
        roleDist.put(UserRole.MANAGER, 5L);
        roleDist.put(UserRole.EMPLOYEE, 28L);

        return new DashboardStatsResponse(
                35L,
                33L,
                4L,
                roleDist,
                new ArrayList<>());
    }

    @QueryMapping
    public List<UserResponse> getAllUsers() {
        UserResponse u1 = new UserResponse(
                1L, "john.doe", "John Doe", "john.doe@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", 3L, "Mary Smith", LocalDate.of(2023, 1, 15), "+1-555-0101",
                "Jane Doe: +1-555-0199", true, LocalDateTime.now().minusHours(2));

        UserResponse u2 = new UserResponse(
                2L, "mary.smith", "Mary Smith", "mary.smith@example.com",
                new HashSet<>(Arrays.asList(UserRole.MANAGER)),
                "HR", null, null, LocalDate.of(2022, 6, 1), "+1-555-0202",
                "Mark Smith: +1-555-0299", true, LocalDateTime.now().minusMinutes(30));

        return Arrays.asList(u1, u2);
    }

    @QueryMapping
    public List<UserResponse> getManagedUsers(@Argument Long managerId) {
        UserResponse managed1 = new UserResponse(
                10L, "alice", "Alice Johnson", "alice@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", managerId != null ? managerId : 2L, null, LocalDate.of(2023, 3, 10),
                "+1-555-0303", "Bob Johnson: +1-555-0399", true, LocalDateTime.now().minusHours(5));

        UserResponse managed2 = new UserResponse(
                11L, "bob", "Bob Martin", "bob@example.com",
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                "Engineering", managerId != null ? managerId : 2L, null, LocalDate.of(2023, 4, 1),
                "+1-555-0404", "Alice Johnson: +1-555-0499", true, LocalDateTime.now().minusHours(1));

        return Arrays.asList(managed1, managed2);
    }

    @QueryMapping
    public List<LeavePolicy> getAllLeavePolicies() {
        LeavePolicy p1 = new LeavePolicy(
                101L, LeaveType.CASUAL, "Casual leave for personal reasons",
                12.0f, 24.0f, true, 1, 5, 1,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)), true,
                LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(2));

        LeavePolicy p2 = new LeavePolicy(
                102L, LeaveType.SICK, "Sick leave for health issues",
                10.0f, 20.0f, true, 1, 7, 0,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE, UserRole.MANAGER)), true,
                LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(5));

        return Arrays.asList(p1, p2);
    }

    @QueryMapping
    public LeavePolicy getLeavePolicyById(@Argument String id) {
        return new LeavePolicy(
                Long.parseLong(id), LeaveType.EARNED, "Earned leave sample policy",
                15.0f, 30.0f, true, 1, 10, 2,
                new HashSet<>(Arrays.asList(UserRole.EMPLOYEE, UserRole.MANAGER)), true,
                LocalDateTime.now().minusDays(3), LocalDateTime.now());
    }

    // Mutation Resolvers
    @MutationMapping
    public UserResponse updateUser(@Argument String userId, @Argument UserUpdateRequest input) {
        return new UserResponse(
                Long.parseLong(userId),
                "updated.user",
                input.getFullName() != null ? input.getFullName() : "Updated User",
                input.getEmail() != null ? input.getEmail() : "updated.user@example.com",
                input.getRoles() != null ? input.getRoles() : new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                input.getDepartment() != null ? input.getDepartment() : "Operations",
                input.getManagerId(),
                null,
                null,
                null,
                null,
                input.getIsActive() != null && input.getIsActive(),
                LocalDateTime.now());
    }

    @MutationMapping
    public LeavePolicy createOrUpdateLeavePolicy(@Argument LeavePolicyRequest input) {
        return new LeavePolicy(
                100L,
                input.getLeaveType(),
                input.getDescription() != null ? input.getDescription() : "Sample leave policy",
                input.getAnnualCredit() != null ? input.getAnnualCredit() : 12.0f,
                input.getMaxAccumulation(),
                input.getIsCarryForward(),
                input.getMinDuration(),
                input.getMaxDuration(),
                input.getNoticeRequired(),
                input.getApplicableRoles() != null ? input.getApplicableRoles()
                        : new HashSet<>(Arrays.asList(UserRole.EMPLOYEE)),
                input.getIsActive(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @MutationMapping
    public ApiResponse deleteLeavePolicy(@Argument String id) {
        return new ApiResponse(true, "Policy deleted (mock)", Map.of("deletedId", id));
    }

    @MutationMapping
    public ApiResponse creditAnnualLeaveForAllUsers() {
        return new ApiResponse(true, "Annual leave credited to all users (mock)", Map.of("credited", true));
    }

    @MutationMapping
    public List<ApiResponse> creditSpecialLeave(@Argument Map<String, Object> input) {
        @SuppressWarnings("unchecked")
        List<String> userIds = (List<String>) input.get("userIds");
        String leaveType = (String) input.get("leaveType");
        Float amount = ((Number) input.get("amount")).floatValue();
        String reason = (String) input.get("reason");

        List<ApiResponse> results = new ArrayList<>();
        for (String uid : userIds) {
            Map<String, Object> data = new HashMap<>();
            data.put("userId", uid);
            data.put("leaveType", leaveType);
            data.put("amount", amount);
            data.put("reason", reason);

            results.add(new ApiResponse(true, "Special leave credited (mock)", data));
        }
        return results;
    }
}