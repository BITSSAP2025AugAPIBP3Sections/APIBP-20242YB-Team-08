package com.sap.bits.api.LeaveScheduler.controller;
import com.sap.bits.api.LeaveScheduler.dto.LeaveApprovalRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/leave-approvals")
@Tag(name = "Leave Approval", description = "Endpoints for leave approval workflow")
public class LeaveApprovalController {



    @GetMapping("/pending")
    @Operation(summary = "Get pending leave applications for approval")
    //Authorize only MANAGER role using JWT token
    public ResponseEntity<List<String>> getPendingLeaveApplications() {
        List<String> pendingLeaves = new ArrayList<>(Arrays.asList("Value 1", "Value 2", "Value 3"));
        return ResponseEntity.ok(pendingLeaves);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve a leave application")
    //Authorize only MANAGER role using JWT token
    public ResponseEntity<String> approveLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalRequest request) {
        String response = "Leave application " + id + " approved.";
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject a leave application")
    //Authorize only MANAGER role using JWT token
    public ResponseEntity<String> rejectLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalRequest request) {
        String response = "Leave application " + id + " rejected.";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/approved")
    @Operation(summary = "Get approved leave applications")
    //Authorize only MANAGER role using JWT token
    public ResponseEntity<List<String>> getApprovedLeaveApplications() {
        List<String> approvedLeaves = new ArrayList<>(Arrays.asList("Value 1", "Value 2", "Value 3"));
        return ResponseEntity.ok(approvedLeaves);
    }

    @GetMapping("/rejected")
    @Operation(summary = "Get rejected leave applications")
    //Authorize only MANAGER role using JWT token
    public ResponseEntity<List<String>> getRejectedLeaveApplications() {
        List<String> rejectedLeaves = new ArrayList<>(Arrays.asList("Value 1", "Value 2", "Value 3"));
        return ResponseEntity.ok(rejectedLeaves);
    }
}