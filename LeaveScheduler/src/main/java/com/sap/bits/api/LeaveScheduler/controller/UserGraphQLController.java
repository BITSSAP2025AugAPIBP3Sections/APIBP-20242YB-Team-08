package com.sap.bits.api.LeaveScheduler.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

@Controller
public class UserGraphQLController {

    @QueryMapping
    public UserResponse getCurrentUserProfile() {
        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setUsername("john.doe");
        response.setFullName("John Doe");
        response.setEmail("john.doe@example.com");
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.EMPLOYEE);
        response.setRoles(roles);
        response.setDepartment("IT");
        response.setManagerId(2L);
        response.setManagerName("Jane Smith");
        response.setJoiningDate(null);
        response.setPhone("1234567890");
        response.setEmergencyContact("9876543210");
        response.setLastLogin(null);
        return response;
    }

    @QueryMapping
    // TODO: Implement proper authorization (e.g., @PreAuthorize("hasRole('EMPLOYEE')"))
    public List<LeaveBalanceResponse> getCurrentUserLeaveBalances() {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(10f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(5f);
        balances.add(sick);
        return balances;
    }

    @QueryMapping
    // TODO: Implement proper authorization (e.g., @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')"))
    public List<LeaveBalanceResponse> getUserLeaveBalances(@Argument Long userId) {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(8f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(2f);
        balances.add(sick);
        return balances;
    }


}
