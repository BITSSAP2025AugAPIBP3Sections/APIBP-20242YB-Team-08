package com.sap.bits.api.LeaveScheduler.dto.response;

import java.util.List;
import java.util.Map;

import com.sap.bits.api.LeaveScheduler.model.LeaveApplication;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsResponse {
    private long totalUsers; // Total number of users in the system
    private long activeUsers; // Number of active users
    private long pendingLeaves; // Total number of pending leave applications
    private Map<UserRole, Long> roleDistribution; // Distribution of users by role
    private List<LeaveApplication> recentLeaveApplications; // List of recent leave applications
}