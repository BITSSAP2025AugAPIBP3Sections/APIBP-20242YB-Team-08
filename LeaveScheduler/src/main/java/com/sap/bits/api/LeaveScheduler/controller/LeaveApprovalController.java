package com.sap.bits.api.LeaveScheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bits.api.LeaveScheduler.dto.request.LeaveApprovalRequest;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.LeaveResponse;
import com.sap.bits.api.LeaveScheduler.service.LeaveApprovalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/leave-approvals")
@Tag(name = "Leave Approval", description = "Endpoints for leave approval workflow")
public class LeaveApprovalController {

    @Autowired
    private LeaveApprovalService leaveApprovalService;

    @GetMapping("/pending")
    @Operation(summary = "Get pending leave applications for approval")
    @PreAuthorize("hasRole('MANAGER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getPendingLeaveApplications() {
        List<LeaveResponse> pendingLeaves = leaveApprovalService.getPendingLeaveApplications();
        return ResponseEntity.ok(pendingLeaves);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve a leave application")
    @PreAuthorize("hasRole('MANAGER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse> approveLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalRequest request) {
        ApiResponse response = leaveApprovalService.approveLeave(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject a leave application")
    @PreAuthorize("hasRole('MANAGER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse> rejectLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalRequest request) {
        ApiResponse response = leaveApprovalService.rejectLeave(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/approved")
    @Operation(summary = "Get approved leave applications")
    @PreAuthorize("hasRole('MANAGER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getApprovedLeaveApplications() {
        List<LeaveResponse> approvedLeaves = leaveApprovalService.getApprovedLeaveApplications();
        return ResponseEntity.ok(approvedLeaves);
    }

    @GetMapping("/rejected")
    @Operation(summary = "Get rejected leave applications")
    @PreAuthorize("hasRole('MANAGER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveResponse>> getRejectedLeaveApplications() {
        List<LeaveResponse> rejectedLeaves = leaveApprovalService.getRejectedLeaveApplications();
        return ResponseEntity.ok(rejectedLeaves);
    }
}
