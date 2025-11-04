package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserGraphQLController {

    @QueryMapping
    public UserResponse currentUserProfile() {
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
    public List<LeaveBalanceResponse> currentUserLeaveBalances() {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(10f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(5f);
        balances.add(sick);
        return balances;
    }
}