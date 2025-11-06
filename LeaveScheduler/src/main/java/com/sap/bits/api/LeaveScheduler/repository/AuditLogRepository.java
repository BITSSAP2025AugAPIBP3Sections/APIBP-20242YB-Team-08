package com.sap.bits.api.LeaveScheduler.repository;

import com.sap.bits.api.LeaveScheduler.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
