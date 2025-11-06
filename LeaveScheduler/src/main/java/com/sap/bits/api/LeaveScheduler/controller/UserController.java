package com.sap.bits.api.LeaveScheduler.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;
import java.util.HashSet;
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints for user profile and management")
public class UserController {

    @GetMapping("/profile")
    @Operation(summary = "Get current user profile")
    @SecurityRequirement(name = "bearerAuth")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserResponse> getCurrentUserProfile() {
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
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leave-balance")
    @Operation(summary = "Get current user leave balances")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveBalanceResponse>> getCurrentUserLeaveBalances() {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(10f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(5f);
        balances.add(sick);
        return ResponseEntity.ok(balances);
    }

    @GetMapping("/{userId}/leave-balance")
    @Operation(summary = "Get leave balances for a specific user (ADMIN or MANAGER only)")
    //will be Autorized using JWT token (role ('ADMIN', 'MANAGER'))
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveBalanceResponse>> getUserLeaveBalances(@PathVariable Long userId) {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(8f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(2f);
        balances.add(sick);

        return ResponseEntity.ok(balances);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users (ADMIN only)")
    // Authorize only ADMIN role using JWT token
    public ResponseEntity<List<UserResponse>> getAllUsers() {
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
        return ResponseEntity.ok(users);
    }

    @GetMapping("/managed")
    @Operation(summary = "Get users managed by current user (MANAGER only)")
    // Authorize only MANAGER role using JWT token
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserResponse>> getManagedUsers() {
        List<UserResponse> users = new ArrayList<>();
        UserResponse user1 = new UserResponse();
        user1.setId(3L);
        user1.setUsername("alice.brown");
        user1.setFullName("Alice Brown");
        user1.setEmail("alice.brown@example.com");
        HashSet<UserRole> roles1 = new HashSet<>();
        roles1.add(UserRole.EMPLOYEE);
        user1.setRoles(roles1);
        user1.setDepartment("Finance");
        user1.setManagerId(2L);
        user1.setManagerName("Jane Smith");
        user1.setJoiningDate(null);
        user1.setPhone("3456789012");
        user1.setEmergencyContact("7654321098");
        user1.setLastLogin(null);
        users.add(user1);
        UserResponse user2 = new UserResponse();
        user2.setId(4L);
        user2.setUsername("bob.white");
        user2.setFullName("Bob White");
        user2.setEmail("bob.white@example.com");
        HashSet<UserRole> roles2 = new HashSet<>();
        roles2.add(UserRole.EMPLOYEE);
        user2.setRoles(roles2);
        user2.setDepartment("Finance");
        user2.setManagerId(2L);
        user2.setManagerName("Jane Smith");
        user2.setJoiningDate(null);
        user2.setPhone("4567890123");
        user2.setEmergencyContact("6543210987");
        user2.setLastLogin(null);
        users.add(user2);
        return ResponseEntity.ok(users);
    }
}