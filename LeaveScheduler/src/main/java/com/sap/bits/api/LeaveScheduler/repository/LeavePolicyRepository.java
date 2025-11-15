package com.sap.bits.api.LeaveScheduler.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {
  Optional<LeavePolicy> findByLeaveType(LeaveType leaveType);

    List<LeavePolicy> findByIsActiveTrue();

    @Query("SELECT lp FROM LeavePolicy lp JOIN lp.applicableRoles ar WHERE ar = :role AND lp.isActive = true")
    List<LeavePolicy> findByApplicableRolesAndActive(@Param("role") UserRole role);

    Optional<LeavePolicy> findByLeaveTypeAndApplicableRolesContaining(LeaveType leaveType, UserRole userRole);
}
