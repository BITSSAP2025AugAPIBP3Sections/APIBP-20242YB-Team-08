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

    @QueryMapping
    public List<UserResponse> allUsers() {
        List<UserResponse> users = new ArrayList<>();
        UserResponse user1 = new UserResponse();
        user1.setId(1L);
        user1.setUsername("john.doe");
        user1.setFullName("John Doe");
        user1.setEmail("john.doe@example.com");
        HashSet<UserRole> roles1 = new HashSet<>();
        roles1.add(UserRole.EMPLOYEE);
        user1.setRoles(roles1);
        user1.setDepartment("IT");
        user1.setManagerId(2L);
        user1.setManagerName("Jane Smith");
        user1.setJoiningDate(null);
        user1.setPhone("1234567890");
        user1.setEmergencyContact("9876543210");
        user1.setLastLogin(null);
        users.add(user1);

        UserResponse user2 = new UserResponse();
        user2.setId(2L);
        user2.setUsername("jane.smith");
        user2.setFullName("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        HashSet<UserRole> roles2 = new HashSet<>();
        roles2.add(UserRole.MANAGER);
        user2.setRoles(roles2);
        user2.setDepartment("HR");
        user2.setManagerId(null);
        user2.setManagerName(null);
        user2.setJoiningDate(null);
        user2.setPhone("2345678901");
        user2.setEmergencyContact("8765432109");
        user2.setLastLogin(null);
        users.add(user2);
        return users;
    }
}