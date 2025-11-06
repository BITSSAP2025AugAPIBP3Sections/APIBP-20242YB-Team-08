package com.sap.bits.api.LeaveScheduler.repository;


import com.sap.bits.api.LeaveScheduler.model.LeaveBalance;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    List<LeaveBalance> findByUser(User user);

    Optional<LeaveBalance> findByUserAndLeaveTypeAndYear(User user, LeaveType leaveType, Integer year);

    List<LeaveBalance> findByUserAndYear(User user, Integer year);

    List<LeaveBalance> findByYear(Integer year);
}