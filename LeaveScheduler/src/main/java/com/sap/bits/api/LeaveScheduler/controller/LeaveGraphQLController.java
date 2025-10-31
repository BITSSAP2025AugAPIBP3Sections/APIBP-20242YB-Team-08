package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.LeaveResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaveGraphQLController {

    @QueryMapping
    public List<LeaveBalanceResponse> userLeaveBalances(Long userId) {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(8f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(2f);
        balances.add(sick);
        return balances;
    }

    @QueryMapping
    public List<LeaveResponse> currentUserLeaves() {
        List<LeaveResponse> leaves = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plusDays(2));
        leave.setReason("Vacation");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now());
        leaves.add(leave);
        return leaves;
    }

    @QueryMapping
    public List<LeaveResponse> managedUsers() {
        List<LeaveResponse> leaves = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(2L);
        leave.setUserId(3L);
        leave.setUsername("alice.brown");
        leave.setStartDate(LocalDate.now().plusDays(5));
        leave.setEndDate(LocalDate.now().plusDays(7));
        leave.setReason("Medical");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now());
        leaves.add(leave);
        return leaves;
    }
}