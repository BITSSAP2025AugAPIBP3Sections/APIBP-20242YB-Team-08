package com.sap.bits.api.LeaveScheduler.controller.graphql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sap.bits.api.LeaveScheduler.dto.response.LeaveBalanceResponse;
import com.sap.bits.api.LeaveScheduler.dto.response.LeaveResponse;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveStatus;

import lombok.Data;

@Controller
public class LeaveGraphQLController {

    private final ConcurrentMap<Long, LeaveResponse> leaveApplications = new ConcurrentHashMap<>();

    @QueryMapping
    public List<LeaveBalanceResponse> userLeaveBalances(@Argument Long userId) {
        List<LeaveBalanceResponse> balances = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(8f);
        balances.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(2f);
        balances.add(sick);
        return balances;
    }

@Data
class LeaveApplicationInput {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String leaveType;
}

    @QueryMapping
    public List<LeaveResponse> currentUserLeaves() {
        return new ArrayList<>(leaveApplications.values());
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

    @MutationMapping
    public LeaveResponse applyLeave(@Argument LeaveApplicationInput request) {
        LeaveResponse response = new LeaveResponse();
        Long id = generateId();
        response.setId(id);
        response.setUserId(1L); // Assuming current user id is 1
        response.setUsername("john.doe"); // Assuming current username
        response.setStartDate(request.getStartDate());
        response.setEndDate(request.getEndDate());
        response.setReason(request.getReason());
        response.setStatus(LeaveStatus.PENDING);
        response.setAppliedOn(LocalDateTime.now());
        leaveApplications.put(id, response);
        return response;
    }

    private Long generateId() {
        return System.currentTimeMillis();
    }

    @QueryMapping
    public List<LeaveBalanceResponse> getLeaveEligibilityDetails() {
        List<LeaveBalanceResponse> eligibilityDetails = new ArrayList<>();
        LeaveBalanceResponse annual = new LeaveBalanceResponse();
        annual.setBalance(10f);
        eligibilityDetails.add(annual);
        LeaveBalanceResponse sick = new LeaveBalanceResponse();
        sick.setBalance(5f);
        eligibilityDetails.add(sick);
        return eligibilityDetails;
    }

    @QueryMapping
    public List<LeaveResponse> getCurrentUserPendingLeaves() {
        List<LeaveResponse> leaves = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(2L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().plusDays(5));
        leave.setEndDate(LocalDate.now().plusDays(7));
        leave.setReason("Medical");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now());
        leaves.add(leave);
        return leaves;
    }

    @QueryMapping
    public LeaveResponse getLeaveById(@Argument Long id) {
        LeaveResponse response = new LeaveResponse();
        response.setId(id);
        response.setUserId(1L);
        response.setUsername("john.doe");
        response.setStartDate(LocalDate.now());
        response.setEndDate(LocalDate.now().plusDays(2));
        response.setReason("Vacation");
        response.setStatus(null);
        response.setAppliedOn(LocalDateTime.now());
        return response;
    }

    @MutationMapping
    public String withdrawLeave(@Argument Long id) {
        return "Leave application " + id + " withdrawn.";
    }

    @QueryMapping
    public List<LeaveResponse> getLeaveHistory() {
        List<LeaveResponse> history = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().minusDays(30));
        leave.setEndDate(LocalDate.now().minusDays(28));
        leave.setReason("Personal");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now().minusDays(30));
        history.add(leave);
        return history;
    }

    @QueryMapping
    public List<LeaveResponse> getLeaveHistoryWithFilters(
            @Argument LocalDate startDate,
            @Argument LocalDate endDate,
            @Argument String leaveType) {
        List<LeaveResponse> history = new ArrayList<>();
        LeaveResponse leave = new LeaveResponse();
        leave.setId(1L);
        leave.setUserId(1L);
        leave.setUsername("john.doe");
        leave.setStartDate(LocalDate.now().minusDays(30));
        leave.setEndDate(LocalDate.now().minusDays(28));
        leave.setReason("Personal");
        leave.setStatus(null);
        leave.setAppliedOn(LocalDateTime.now().minusDays(30));
        history.add(leave);
        return history;
    }

    @QueryMapping
    public LeaveResponse.LeaveStats getLeaveStats() {
        LeaveResponse.LeaveStats stats = new LeaveResponse.LeaveStats();
        stats.setTotalBalance(15f);
        stats.setTotalUsed(5f);
        stats.setPendingLeaves(1);
        return stats;
    }
}
