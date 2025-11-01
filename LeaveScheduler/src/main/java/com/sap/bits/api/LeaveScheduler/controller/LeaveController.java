package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.LeaveResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/leave-applications")
@Tag(name = "Leave Management", description = "Endpoints for leave applications")
public class LeaveController {

    @PostMapping
    @Operation(summary = "Apply for leave")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<LeaveResponse> applyLeave(@RequestBody Object request) {
        LeaveResponse response = new LeaveResponse();
        response.setId(1L);
        response.setUserId(1L);
        response.setUsername("john.doe");
        response.setStartDate(LocalDate.now());
        response.setEndDate(LocalDate.now().plusDays(2));
        response.setReason("Vacation");
        response.setStatus(null);
        response.setAppliedOn(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get current user's leave applications")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getCurrentUserLeaves() {
        List<LeaveResponse> leaves = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plusDays(2));
        leave.setReason("Vacation");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now());
        leaves.add(leave);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/eligibility")
    @Operation(summary = "Get leave eligibility details for the current user")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveBalanceResponse>> getLeaveEligibilityDetails() {
        List<LeaveBalanceResponse> eligibilityDetails = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(10f);
        eligibilityDetails.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(5f);
        eligibilityDetails.add(sick);
        return ResponseEntity.ok(eligibilityDetails);
    }

    @GetMapping("/pending")
    @Operation(summary = "Get current user's pending leave applications")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getCurrentUserPendingLeaves() {
        List<LeaveResponse> leaves = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(2L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().plusDays(5));
        leave.setEndDate(LocalDate.now().plusDays(7));
        leave.setReason("Medical");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now());
        leaves.add(leave);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get leave application by ID")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<LeaveResponse> getLeaveById(@PathVariable Long id) {
        LeaveResponse response = new LeaveResponse();
        response.setId(id);
        response.setUserId(1L);
        response.setUsername("john.doe");
        response.setStartDate(LocalDate.now());
        response.setEndDate(LocalDate.now().plusDays(2));
        response.setReason("Vacation");
        response.setStatus(null);
        response.setAppliedOn(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/withdraw")
    @Operation(summary = "Withdraw a leave application")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> withdrawLeave(@PathVariable Long id) {
        return ResponseEntity.ok("Leave application " + id + " withdrawn.");
    }

    @GetMapping("/history")
    @Operation(summary = "Get current user's leave history")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getLeaveHistory() {
        List<LeaveResponse> history = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().minusDays(30));
        leave.setEndDate(LocalDate.now().minusDays(28));
        leave.setReason("Personal");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now().minusDays(30));
        history.add(leave);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/filter-history")
    @Operation(summary = "Get leave history with optional filters")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getLeaveHistory(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String leaveType) {
        List<LeaveResponse> history = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().minusDays(30));
        leave.setEndDate(LocalDate.now().minusDays(28));
        leave.setReason("Personal");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now().minusDays(30));
        history.add(leave);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/stats")
    @Operation(summary = "Get current user's leave statistics")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<LeaveResponse.LeaveStats> getLeaveStats() {
        LeaveResponse.LeaveStats stats = new LeaveResponse.LeaveStats();
        stats.setTotalBalance(15f);
        stats.setTotalUsed(5f);
        stats.setPendingLeaves(1);
        return ResponseEntity.ok(stats);
    }
}