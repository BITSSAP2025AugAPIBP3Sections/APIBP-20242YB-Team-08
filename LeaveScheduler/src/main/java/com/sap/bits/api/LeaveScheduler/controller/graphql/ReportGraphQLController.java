package com.sap.bits.api.LeaveScheduler.controller.graphql;

import com.sap.bits.api.LeaveScheduler.model.Holiday;
import com.sap.bits.api.LeaveScheduler.model.LeaveApplication;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveStatus;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Tag(name = "Reports (GraphQL)", description = "GraphQL endpoints for reports")
public class ReportGraphQLController {

    @QueryMapping(name = "leaveUsageReport")
    public List<LeaveApplication> getLeaveUsageReport() {
        return getSampleLeaveApplications();
    }

    @QueryMapping(name = "pendingApprovalsReport")
    public List<LeaveApplication> getPendingApprovalsReport() {
        return getSampleLeaveApplications().stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.PENDING)
                .collect(Collectors.toList());
    }

    @QueryMapping(name = "holidayScheduleReport")
    public List<Holiday> getHolidayScheduleReport() {
        return getSampleHolidays();
    }



    private List<LeaveApplication> getSampleLeaveApplications() {
        List<LeaveApplication> leaves = new ArrayList<>();

        LeaveApplication leave1 = new LeaveApplication();
        leave1.setId(1L);
        leave1.setStartDate(LocalDate.now().minusDays(10));
        leave1.setEndDate(LocalDate.now().minusDays(8));
        leave1.setLeaveType(LeaveType.CASUAL);
        leave1.setReason("Vacation");
        leave1.setStatus(LeaveStatus.APPROVED);
        leave1.setAppliedOn(LocalDateTime.now().minusDays(15));
        leaves.add(leave1);

        LeaveApplication leave2 = new LeaveApplication();
        leave2.setId(2L);
        leave2.setStartDate(LocalDate.now().plusDays(2));
        leave2.setEndDate(LocalDate.now().plusDays(4));
        leave2.setLeaveType(LeaveType.SICK);
        leave2.setReason("Medical leave");
        leave2.setStatus(LeaveStatus.PENDING);
        leave2.setAppliedOn(LocalDateTime.now().minusDays(1));
        leaves.add(leave2);

        return leaves;
    }

    private List<Holiday> getSampleHolidays() {
        List<Holiday> holidays = new ArrayList<>();

        Holiday h1 = new Holiday();
        h1.setId(1L);
        h1.setName("Independence Day");
        h1.setDate(LocalDate.of(LocalDate.now().getYear(), 8, 15));
        h1.setType("National");
        h1.setDescription("Public holiday");
        h1.setIsRecurring(true);
        holidays.add(h1);

        Holiday h2 = new Holiday();
        h2.setId(2L);
        h2.setName("New Year");
        h2.setDate(LocalDate.of(LocalDate.now().getYear() + 1, 1, 1));
        h2.setType("Organizational");
        h2.setDescription("Start of the new year");
        h2.setIsRecurring(true);
        holidays.add(h2);

        return holidays;
    }
}