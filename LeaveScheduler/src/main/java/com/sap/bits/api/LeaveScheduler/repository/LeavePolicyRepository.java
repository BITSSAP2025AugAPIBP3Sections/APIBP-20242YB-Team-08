package com.sap.bits.api.LeaveScheduler.repository;

import com.sap.bits.api.LeaveScheduler.model.LeavePolicy;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {

    @Query("SELECT lp FROM LeavePolicy lp JOIN lp.applicableRoles ar WHERE ar = :role AND lp.isActive = true")
    List<LeavePolicy> findByApplicableRolesAndActive(@Param("role") UserRole role);
}
