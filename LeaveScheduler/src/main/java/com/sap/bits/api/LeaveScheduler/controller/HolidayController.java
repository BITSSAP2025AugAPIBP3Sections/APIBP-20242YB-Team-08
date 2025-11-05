package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.model.Holiday;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@Tag(name = "Holiday Management", description = "Endpoints for managing holidays")
public class HolidayController {

    @PostMapping
    @Operation(summary = "Create a new holiday (ADMIN only)")
    //TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> createHoliday(@Valid @RequestBody Holiday holiday) {
        // TODO: Replace with actual holidayService.createHoliday() when service is implemented
        Holiday createdHoliday = mockCreateHoliday(holiday);
        return ResponseEntity.ok(createdHoliday);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple holidays (ADMIN only)")
    //TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> createHolidays(@Valid @RequestBody List<Holiday> holidays) {
        // TODO: Replace with actual holidayService.createHolidays() when service is implemented
        List<Holiday> createdHolidays = mockCreateHolidays(holidays);
        return ResponseEntity.ok(createdHolidays);
    }

    @GetMapping("/")
    @Operation(summary = "Get all holidays")
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        // TODO: Replace with actual holidayService.getAllHolidays() when service is implemented
        List<Holiday> holidays = mockGetAllHolidays();
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Get holiday by ID")
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {
        // TODO: Replace with actual holidayService.getHolidayById() when service is implemented
        Holiday holiday = mockGetHolidayById(id);
        return ResponseEntity.ok(holiday);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get holidays by year")
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getHolidaysByYear(@PathVariable Integer year) {
        // TODO: Replace with actual holidayService.getHolidaysByYear() when service is implemented
        List<Holiday> holidays = mockGetHolidaysByYear(year);
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/month/{month}/year/{year}")
    @Operation(summary = "Get holidays by month and year")
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getHolidaysByMonthAndYear(
            @PathVariable Integer month,
            @PathVariable Integer year) {
        // TODO: Replace with actual holidayService.getHolidaysByMonthAndYear() when service is implemented
        List<Holiday> holidays = mockGetHolidaysByMonthAndYear(month, year);
        return ResponseEntity.ok(holidays);
    }

    @PutMapping("/{id:\\d+}")
    @Operation(summary = "Update a holiday (ADMIN only)")
    //TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<Holiday> updateHoliday(
            @PathVariable Long id,
            @Valid @RequestBody Holiday holidayDetails) {
        // TODO: Replace with actual holidayService.updateHoliday() when service is implemented
        Holiday updatedHoliday = mockUpdateHoliday(id, holidayDetails);
        return ResponseEntity.ok(updatedHoliday);
    }

    @DeleteMapping("/{id:\\d+}")
    @Operation(summary = "Delete a holiday (ADMIN only)")
    //TODO: Add @PreAuthorize("hasRole('ADMIN')") when Spring Security is configured
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<String> deleteHoliday(@PathVariable Long id) {
        // TODO: Replace with actual holidayService.deleteHoliday() when service is implemented
        String response = mockDeleteHoliday(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calendar")
    @Operation(summary = "Get calendar view of holidays")
    //TODO: Add @SecurityRequirement(name = "bearerAuth") when security is configured
    public ResponseEntity<List<Holiday>> getCalendarView() {
        // TODO: Replace with actual holidayService.getAllHolidays() when service is implemented
        List<Holiday> holidays = mockGetAllHolidays();
        return ResponseEntity.ok(holidays);
    }

    // ============= MOCK METHODS - Replace with actual service calls =============
    
    private Holiday mockCreateHoliday(Holiday holiday) {
        holiday.setId(System.currentTimeMillis());
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        return holiday;
    }
    
    private List<Holiday> mockCreateHolidays(List<Holiday> holidays) {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < holidays.size(); i++) {
            holidays.get(i).setId(System.currentTimeMillis() + i);
            holidays.get(i).setCreatedAt(now);
            holidays.get(i).setUpdatedAt(now);
        }
        return holidays;
    }
    
    private List<Holiday> mockGetAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday newYear = new Holiday();
        newYear.setId(1L);
        newYear.setName("New Year's Day");
        newYear.setDate(LocalDate.of(2024, 1, 1));
        newYear.setType("National");
        newYear.setDescription("Celebration of the new year");
        newYear.setIsRecurring(true);
        newYear.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        newYear.setUpdatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(newYear);
        
        Holiday independence = new Holiday();
        independence.setId(2L);
        independence.setName("Independence Day");
        independence.setDate(LocalDate.of(2024, 7, 4));
        independence.setType("National");
        independence.setDescription("National independence celebration");
        independence.setIsRecurring(true);
        independence.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        independence.setUpdatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(independence);
        
        Holiday christmas = new Holiday();
        christmas.setId(3L);
        christmas.setName("Christmas");
        christmas.setDate(LocalDate.of(2024, 12, 25));
        christmas.setType("National");
        christmas.setDescription("Christmas holiday");
        christmas.setIsRecurring(true);
        christmas.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        christmas.setUpdatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(christmas);
        
        return holidays;
    }
    
    private Holiday mockGetHolidayById(Long id) {
        Holiday holiday = new Holiday();
        holiday.setId(id);
        holiday.setName("Sample Holiday " + id);
        holiday.setDate(LocalDate.of(2024, 1, 1));
        holiday.setType("National");
        holiday.setDescription("Sample holiday with ID: " + id);
        holiday.setIsRecurring(false);
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        return holiday;
    }
    
    private List<Holiday> mockGetHolidaysByYear(Integer year) {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday holiday1 = new Holiday();
        holiday1.setId(1L);
        holiday1.setName("Holiday 1 in " + year);
        holiday1.setDate(LocalDate.of(year, 6, 15));
        holiday1.setType("National");
        holiday1.setDescription("First holiday of " + year);
        holiday1.setIsRecurring(true);
        holiday1.setCreatedAt(LocalDateTime.now());
        holiday1.setUpdatedAt(LocalDateTime.now());
        holidays.add(holiday1);
        
        Holiday holiday2 = new Holiday();
        holiday2.setId(2L);
        holiday2.setName("Holiday 2 in " + year);
        holiday2.setDate(LocalDate.of(year, 9, 10));
        holiday2.setType("Organizational");
        holiday2.setDescription("Second holiday of " + year);
        holiday2.setIsRecurring(false);
        holiday2.setCreatedAt(LocalDateTime.now());
        holiday2.setUpdatedAt(LocalDateTime.now());
        holidays.add(holiday2);
        
        return holidays;
    }
    
    private List<Holiday> mockGetHolidaysByMonthAndYear(Integer month, Integer year) {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday holiday = new Holiday();
        holiday.setId(1L);
        holiday.setName("Holiday in " + month + "/" + year);
        holiday.setDate(LocalDate.of(year, month, 15));
        holiday.setType("National");
        holiday.setDescription("Holiday occurring in month " + month + " of year " + year);
        holiday.setIsRecurring(true);
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        holidays.add(holiday);
        
        return holidays;
    }
    
    private Holiday mockUpdateHoliday(Long id, Holiday holidayDetails) {
        holidayDetails.setId(id);
        holidayDetails.setUpdatedAt(LocalDateTime.now());
        if (holidayDetails.getCreatedAt() == null) {
            holidayDetails.setCreatedAt(LocalDateTime.now());
        }
        return holidayDetails;
    }
    
    private String mockDeleteHoliday(Long id) {
        return "Holiday " + id + " deleted successfully.";
    }
}