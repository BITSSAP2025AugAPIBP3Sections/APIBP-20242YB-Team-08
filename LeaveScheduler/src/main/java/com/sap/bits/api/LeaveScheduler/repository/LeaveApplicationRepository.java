package com.sap.bits.api.LeaveScheduler.repository;

import com.sap.bits.api.LeaveScheduler.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
}
