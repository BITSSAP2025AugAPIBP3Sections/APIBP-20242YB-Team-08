package com.sap.bits.api.LeaveScheduler.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Set<UserRole> roles;
    private String department;
    private Long managerId;
    private String managerName;
    private LocalDate joiningDate;
    private String phone;
    private String emergencyContact;
    private boolean isActive;
    private LocalDateTime lastLogin;
}