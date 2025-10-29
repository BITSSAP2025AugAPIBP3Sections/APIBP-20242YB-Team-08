package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.model.AuditLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@Tag(name = "Audit Logs", description = "Endpoints for viewing admin audit logs")
public class AuditLogController {

    @GetMapping
    @Operation(summary = "Get all audit logs (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        List<AuditLog> logs = new ArrayList<>();
        
        AuditLog log1 = new AuditLog();
        log1.setId(1L);
        log1.setAdminId(100L);
        log1.setAction("USER_LOGIN");
        log1.setDetails("Admin user logged in");
        log1.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        logs.add(log1);
        
        AuditLog log2 = new AuditLog();
        log2.setId(2L);
        log2.setAdminId(100L);
        log2.setAction("HOLIDAY_CREATED");
        log2.setDetails("New holiday created: New Year's Day");
        log2.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 11, 0));
        logs.add(log2);
        
        AuditLog log3 = new AuditLog();
        log3.setId(3L);
        log3.setAdminId(101L);
        log3.setAction("LEAVE_APPROVED");
        log3.setDetails("Leave application approved by manager");
        log3.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 14, 30));
        logs.add(log3);
        
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get audit logs by user ID (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<List<AuditLog>> getAuditLogsByUserId(@PathVariable Long userId) {
        List<AuditLog> logs = new ArrayList<>();
        
        AuditLog log1 = new AuditLog();
        log1.setId(1L);
        log1.setAdminId(userId);
        log1.setAction("LOGIN_ATTEMPT");
        log1.setDetails("User " + userId + " attempted login");
        log1.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 9, 0));
        logs.add(log1);
        
        AuditLog log2 = new AuditLog();
        log2.setId(2L);
        log2.setAdminId(userId);
        log2.setAction("LEAVE_SUBMITTED");
        log2.setDetails("User " + userId + " submitted leave application");
        log2.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 9, 30));
        logs.add(log2);
        
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/actions/{action}")
    @Operation(summary = "Get audit logs by action type (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<List<AuditLog>> getAuditLogsByAction(@PathVariable String action) {
        List<AuditLog> logs = new ArrayList<>();
        
        AuditLog log1 = new AuditLog();
        log1.setId(1L);
        log1.setAdminId(123L);
        log1.setAction(action);
        log1.setDetails("Action: " + action + " performed by User123");
        log1.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 12, 0));
        logs.add(log1);
        
        AuditLog log2 = new AuditLog();
        log2.setId(2L);
        log2.setAdminId(456L);
        log2.setAction(action);
        log2.setDetails("Action: " + action + " performed by User456");
        log2.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 13, 0));
        logs.add(log2);
        
        return ResponseEntity.ok(logs);
    }
}