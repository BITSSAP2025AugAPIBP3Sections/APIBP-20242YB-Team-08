package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.model.LeaveApplication;
import com.sap.bits.api.LeaveScheduler.model.Holiday;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveStatus;
import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Endpoints for generating and exporting reports")
public class ReportController {

    @GetMapping("/leave-usage")
    @Operation(summary = "Get leave usage report")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveApplication>> getLeaveUsageReport() {
        List<LeaveApplication> report = getSampleLeaveApplications();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/pending-approvals")
    @Operation(summary = "Get pending approvals report")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<LeaveApplication>> getPendingApprovalsReport() {
        List<LeaveApplication> pending = new ArrayList<>();
        for (LeaveApplication leave : getSampleLeaveApplications()) {
            if (leave.getStatus() == LeaveStatus.PENDING) {
                pending.add(leave);
            }
        }
        return ResponseEntity.ok(pending);
    }

    @GetMapping("/holiday-schedule")
    @Operation(summary = "Get holiday schedule report")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Holiday>> getHolidayScheduleReport() {
        List<Holiday> holidays = getSampleHolidays();
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/leave-usage/export/excel")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Export leave usage report to Excel (mocked data)")
    public ResponseEntity<byte[]> exportLeaveUsageToExcel() {
        // Just returning dummy byte array for now
        byte[] excelData = "Dummy Excel Content".getBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leave-usage.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

    @GetMapping("/leave-usage/export/pdf")
    @Operation(summary = "Export leave usage report to PDF (mocked data)")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<byte[]> exportLeaveUsageToPDF() {
        // Just returning dummy byte array for now
        byte[] pdfData = "Dummy PDF Content".getBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leave-usage.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
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