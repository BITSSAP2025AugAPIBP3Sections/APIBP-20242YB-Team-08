package com.sap.bits.api.LeaveScheduler.controller.graphql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sap.bits.api.LeaveScheduler.model.AuditLog;

@Controller
public class AuditLogGraphQLController {

    // Query Resolvers
    @QueryMapping
    public List<AuditLog> getAllAuditLogs() {
        // TODO: Replace with auditLogRepository.findAll() when repository is implemented
        return createMockAuditLogs();
    }

    /**
     * Placeholder mock data method - replace with actual repository call
     * This mirrors the same mock data from AuditLogController
     */
    private List<AuditLog> createMockAuditLogs() {
        List<AuditLog> logs = new ArrayList<>();
        
        AuditLog log1 = new AuditLog();
        log1.setId(1L);
        log1.setAdminId(100L);
        log1.setAction("USER_LOGIN");
        log1.setDetails("Admin user logged in");
        log1.setActionTimestamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        log1.setLeaveApplication(null);
        logs.add(log1);
        
        AuditLog log2 = new AuditLog();
        log2.setId(2L);
        log2.setAdminId(100L);
        log2.setAction("HOLIDAY_CREATED");
        log2.setDetails("New holiday created: New Year's Day");
        log2.setActionTimestamp(LocalDateTime.of(2024, 1, 14, 14, 15));
        log2.setLeaveApplication(null);
        logs.add(log2);
        
        AuditLog log3 = new AuditLog();
        log3.setId(3L);
        log3.setAdminId(101L);
        log3.setAction("LEAVE_APPROVED");
        log3.setDetails("Leave application #123 approved for user john.doe");
        log3.setActionTimestamp(LocalDateTime.of(2024, 1, 13, 9, 45));
        log3.setLeaveApplication(null);
        logs.add(log3);
        
        AuditLog log4 = new AuditLog();
        log4.setId(4L);
        log4.setAdminId(100L);
        log4.setAction("USER_UPDATED");
        log4.setDetails("User profile updated for jane.smith");
        log4.setActionTimestamp(LocalDateTime.of(2024, 1, 12, 16, 20));
        log4.setLeaveApplication(null);
        logs.add(log4);
        
        return logs;
    }
}
