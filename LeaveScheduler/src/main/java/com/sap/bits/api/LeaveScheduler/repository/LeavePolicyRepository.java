package com.sap.bits.api.LeaveScheduler.repository;

import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {
}
