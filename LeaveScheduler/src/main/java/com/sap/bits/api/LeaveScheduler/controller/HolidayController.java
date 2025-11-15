package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.model.Holiday;
import com.sap.bits.api.LeaveScheduler.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@Tag(name = "Holiday Management", description = "Endpoints for managing holidays")
public class HolidayController {

    private final HolidayService holidayService;
    
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping
    @Operation(summary = "Create a new holiday (ADMIN only)")
    // TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> createHoliday(@Valid @RequestBody Holiday holiday) {
        Holiday createdHoliday = holidayService.createHoliday(holiday);
        return ResponseEntity.ok(createdHoliday);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple holidays (ADMIN only)")
    // TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> createHolidays(@Valid @RequestBody List<Holiday> holidays) {
        List<Holiday> createdHolidays = holidayService.createHolidays(holidays);
        return ResponseEntity.ok(createdHolidays);
    }

    @GetMapping("/")
    @Operation(summary = "Get all holidays")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        List<Holiday> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Get holiday by ID")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {
        Holiday holiday = holidayService.getHolidayById(id);
        return ResponseEntity.ok(holiday);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get holidays by year")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getHolidaysByYear(@PathVariable Integer year) {
        List<Holiday> holidays = holidayService.getHolidaysByYear(year);
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/month/{month}/year/{year}")
    @Operation(summary = "Get holidays by month and year")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getHolidaysByMonthAndYear(
            @PathVariable Integer month,
            @PathVariable Integer year) {
        List<Holiday> holidays = holidayService.getHolidaysByMonthAndYear(month, year);
        return ResponseEntity.ok(holidays);
    }

    @PutMapping("/{id:\\d+}")
    @Operation(summary = "Update a holiday (ADMIN only)")
    // TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> updateHoliday(
            @PathVariable Long id,
            @Valid @RequestBody Holiday holidayDetails) {
        Holiday updatedHoliday = holidayService.updateHoliday(id, holidayDetails);
        return ResponseEntity.ok(updatedHoliday);
    }

    @DeleteMapping("/{id:\\d+}")
    @Operation(summary = "Delete a holiday (ADMIN only)")
    // TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<ApiResponse> deleteHoliday(@PathVariable Long id) {
        ApiResponse response = holidayService.deleteHoliday(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calendar")
    @Operation(summary = "Get calendar view of holidays")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getCalendarView() {
        List<Holiday> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(holidays);
    }

    // Additional endpoints based on HolidayService functionality
    
    @GetMapping("/date-range")
    @Operation(summary = "Get holidays between dates")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getHolidaysBetweenDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Holiday> holidays = holidayService.getHolidaysBetweenDates(startDate, endDate);
        return ResponseEntity.ok(holidays);
    }
    
    @GetMapping("/check")
    @Operation(summary = "Check if a date is a holiday")
    // TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Boolean> isHoliday(@RequestParam LocalDate date) {
        boolean isHoliday = holidayService.isHoliday(date);
        return ResponseEntity.ok(isHoliday);
    }
}