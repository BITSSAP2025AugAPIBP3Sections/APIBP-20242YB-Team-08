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
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<Holiday> createHoliday(@Valid @RequestBody Holiday holiday) {
        // Set creation timestamp
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        
        // Mock ID assignment (in real app, this would be done by database)
        holiday.setId(System.currentTimeMillis());
        
        return ResponseEntity.ok(holiday);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple holidays (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<List<Holiday>> createHolidays(@Valid @RequestBody List<Holiday> holidays) {
        LocalDateTime now = LocalDateTime.now();
        
        for (Holiday holiday : holidays) {
            holiday.setId(System.currentTimeMillis() + holidays.indexOf(holiday));
            holiday.setCreatedAt(now);
            holiday.setUpdatedAt(now);
        }
        
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/")
    @Operation(summary = "Get all holidays")
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday newYear = new Holiday();
        newYear.setId(1L);
        newYear.setName("New Year's Day");
        newYear.setDate(LocalDate.of(2024, 1, 1));
        newYear.setType("National");
        newYear.setDescription("Celebration of the new year");
        newYear.setIsRecurring(true);
        newYear.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(newYear);
        
        Holiday independence = new Holiday();
        independence.setId(2L);
        independence.setName("Independence Day");
        independence.setDate(LocalDate.of(2024, 7, 4));
        independence.setType("National");
        independence.setDescription("National independence celebration");
        independence.setIsRecurring(true);
        independence.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(independence);
        
        Holiday christmas = new Holiday();
        christmas.setId(3L);
        christmas.setName("Christmas");
        christmas.setDate(LocalDate.of(2024, 12, 25));
        christmas.setType("National");
        christmas.setDescription("Christmas holiday");
        christmas.setIsRecurring(true);
        christmas.setCreatedAt(LocalDateTime.of(2024, 1, 1, 0, 0));
        holidays.add(christmas);
        
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get holiday by ID")
    public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {
        Holiday holiday = new Holiday();
        holiday.setId(id);
        holiday.setName("Sample Holiday " + id);
        holiday.setDate(LocalDate.of(2024, 1, 1));
        holiday.setType("National");
        holiday.setDescription("Sample holiday with ID: " + id);
        holiday.setIsRecurring(false);
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        
        return ResponseEntity.ok(holiday);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Get holidays by year")
    public ResponseEntity<List<Holiday>> getHolidaysByYear(@PathVariable Integer year) {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday holiday1 = new Holiday();
        holiday1.setId(1L);
        holiday1.setName("Holiday 1 in " + year);
        holiday1.setDate(LocalDate.of(year, 6, 15));
        holiday1.setType("National");
        holiday1.setDescription("First holiday of " + year);
        holiday1.setIsRecurring(true);
        holidays.add(holiday1);
        
        Holiday holiday2 = new Holiday();
        holiday2.setId(2L);
        holiday2.setName("Holiday 2 in " + year);
        holiday2.setDate(LocalDate.of(year, 9, 10));
        holiday2.setType("Organizational");
        holiday2.setDescription("Second holiday of " + year);
        holiday2.setIsRecurring(false);
        holidays.add(holiday2);
        
        return ResponseEntity.ok(holidays);
    }

    @GetMapping("/month/{month}/year/{year}")
    @Operation(summary = "Get holidays by month and year")
    public ResponseEntity<List<Holiday>> getHolidaysByMonthAndYear(
            @PathVariable Integer month,
            @PathVariable Integer year) {
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday holiday = new Holiday();
        holiday.setId(1L);
        holiday.setName("Holiday in " + month + "/" + year);
        holiday.setDate(LocalDate.of(year, month, 15));
        holiday.setType("National");
        holiday.setDescription("Holiday occurring in month " + month + " of year " + year);
        holiday.setIsRecurring(true);
        holidays.add(holiday);
        
        return ResponseEntity.ok(holidays);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a holiday (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<Holiday> updateHoliday(
            @PathVariable Long id,
            @Valid @RequestBody Holiday holidayDetails) {
        holidayDetails.setId(id);
        holidayDetails.setUpdatedAt(LocalDateTime.now());
        
        return ResponseEntity.ok(holidayDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a holiday (ADMIN only)")
    //Authorize only ADMIN role using JWT token
    public ResponseEntity<String> deleteHoliday(@PathVariable Long id) {
        String response = "Holiday " + id + " deleted successfully.";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calendar")
    @Operation(summary = "Get calendar view of holidays")
    public ResponseEntity<List<Holiday>> getCalendarView() {
        // Reuse the getAllHolidays logic for calendar view
        List<Holiday> holidays = new ArrayList<>();
        
        Holiday newYear = new Holiday();
        newYear.setId(1L);
        newYear.setName("New Year's Day");
        newYear.setDate(LocalDate.of(2024, 1, 1));
        newYear.setType("National");
        newYear.setDescription("Celebration of the new year");
        newYear.setIsRecurring(true);
        holidays.add(newYear);
        
        Holiday independence = new Holiday();
        independence.setId(2L);
        independence.setName("Independence Day");
        independence.setDate(LocalDate.of(2024, 7, 4));
        independence.setType("National");
        independence.setDescription("National independence celebration");
        independence.setIsRecurring(true);
        holidays.add(independence);
        
        Holiday christmas = new Holiday();
        christmas.setId(3L);
        christmas.setName("Christmas");
        christmas.setDate(LocalDate.of(2024, 12, 25));
        christmas.setType("National");
        christmas.setDescription("Christmas holiday");
        christmas.setIsRecurring(true);
        holidays.add(christmas);
        
        return ResponseEntity.ok(holidays);
    }
}
