package com.sap.bits.api.LeaveScheduler.service;


import com.sap.bits.api.LeaveScheduler.dto.request.LeavePolicyRequest;
import com.sap.bits.api.LeaveScheduler.dto.request.UserUpdateRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.DashboardStatsResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.AuditLog;
import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveStatus;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;
import com.sap.bits.api.LeaveScheduler.repository.AuditLogRepository;
import com.sap.bits.api.LeaveScheduler.repository.LeaveApplicationRepository;
import com.sap.bits.api.LeaveScheduler.repository.LeavePolicyRepository;
import com.sap.bits.api.LeaveScheduler.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeavePolicyRepository leavePolicyRepository;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuditLogRepository auditLogRepository;

    public DashboardStatsResponse getDashboardStats() throws Exception {
        User currentUser = userService.getCurrentUser();

        // Verify admin role
        // Total users count
        // Active users count
        // Total pending leaves
        // Role distribution
        // Recent leave applications
        DashboardStatsResponse stats = new DashboardStatsResponse();
        return stats;
    }

    private void logAdminAction(String action, String details) {
        User currentUser = userService.getCurrentUser();
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDetails(details);
        log.setAdminId(currentUser.getId());
        log.setActionTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    /**
     * Get all users (admin only)
     */
    public List<UserResponse> getAllUsers() {
        User currentUser = userService.getCurrentUser();

        // Verify admin role
        //Dummy UserResponse return

        List <UserResponse> users = new ArrayList<>();
        return users;
    }

    /**
     * Update user details (admin only)
     */
    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User currentUser = userService.getCurrentUser();

        // Verify admin role
        // Update user fields
        // Check if email is already in use
        // Validate manager role - must be MANAGER or higher

        User updatedUser = new User();
        return convertToUserResponse(updatedUser);
    }

    /**
     * Create or update leave policy
     */
    @Transactional
    public LeavePolicy createOrUpdateLeavePolicy(LeavePolicyRequest request) {
        User currentUser = userService.getCurrentUser();

        // Verify admin role
        // Create new or update existing policy
        // Update fields
        return new LeavePolicy();
    }

    /**
     * Get leave policy by ID
     */
    public LeavePolicy getLeavePolicyById(Long id) {
        User currentUser = userService.getCurrentUser();

        // Verify admin role for detailed access

        return new LeavePolicy();
    }

    /**
     * Get all leave policies
     */
    public List<LeavePolicy> getAllLeavePolicies() {
        User currentUser = userService.getCurrentUser();

        // Verify admin role for full list
        return new ArrayList<>();
    }

    /**
     * Delete leave policy
     */
    @Transactional
    public ApiResponse deleteLeavePolicy(Long id) {
        User currentUser = userService.getCurrentUser();

        // Verify admin role

        // Check if there are any leave balances using this policy
        if (!leaveApplicationRepository.findAll().isEmpty()) {
            // Instead of deleting, just mark as inactive
            return new ApiResponse(true, "Leave policy marked as inactive successfully");
        }

        logAdminAction("DELETE_LEAVE_POLICY",
                "Policy ID: " + "<Leave policy Id>" + ", Type: " + "<Leave policy Type>");

        return new ApiResponse(true, "Leave policy deleted successfully");
    }

    /**
     * Convert User to UserResponse
     */
    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setDepartment(user.getDepartment());
        response.setRoles(user.getRoles());
        response.setJoiningDate(user.getJoiningDate());
        response.setEmergencyContact(user.getEmergencyContact());
        response.setPhone(user.getPhone());
        response.setLastLogin(user.getLastLogin());

        if (user.getManager() != null) {
            response.setManagerId(user.getManager().getId());
            response.setManagerName(user.getManager().getFullName());
        }

        response.setActive(user.isActive());

        return response;
    }
}