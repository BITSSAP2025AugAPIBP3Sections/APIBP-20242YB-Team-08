package com.sap.bits.api.LeaveScheduler.controller.graphql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sap.bits.api.LeaveScheduler.model.Holiday;

@Controller
public class HolidayGraphQLController {

    // Query Resolvers
    @QueryMapping
    public List<Holiday> getAllHolidays() {
        // TODO: Replace with holidayService.getAllHolidays() when service is implemented
        return mockGetAllHolidays();
    }

    @QueryMapping
    public Holiday getHolidayById(@Argument Long id) {
        // TODO: Replace with holidayService.getHolidayById(id) when service is implemented
        return mockGetHolidayById(id);
    }

    @QueryMapping
    public List<Holiday> getHolidaysByYear(@Argument Integer year) {
        // TODO: Replace with holidayService.getHolidaysByYear(year) when service is implemented
        return mockGetHolidaysByYear(year);
    }

    @QueryMapping
    public List<Holiday> getHolidaysByMonthAndYear(@Argument Integer month, @Argument Integer year) {
        // TODO: Replace with holidayService.getHolidaysByMonthAndYear(month, year) when service is implemented
        return mockGetHolidaysByMonthAndYear(month, year);
    }

    @QueryMapping
    public List<Holiday> getCalendarView() {
        // TODO: Replace with holidayService.getAllHolidays() when service is implemented
        return mockGetAllHolidays();
    }

    // Mutation Resolvers
    @MutationMapping
    public Holiday createHoliday(@Argument Holiday input) {
        // TODO: Replace with holidayService.createHoliday(input) when service is implemented
        return mockCreateHoliday(input);
    }

    @MutationMapping
    public List<Holiday> createHolidays(@Argument List<Holiday> input) {
        // TODO: Replace with holidayService.createHolidays(input) when service is implemented
        return mockCreateHolidays(input);
    }

    @MutationMapping
    public Holiday updateHoliday(@Argument Long id, @Argument Holiday input) {
        // TODO: Replace with holidayService.updateHoliday(id, input) when service is implemented
        return mockUpdateHoliday(id, input);
    }

    @MutationMapping
    public String deleteHoliday(@Argument Long id) {
        // TODO: Replace with holidayService.deleteHoliday(id) when service is implemented
        return mockDeleteHoliday(id);
    }

    // ============= PLACEHOLDER MOCK METHODS - Replace with actual service calls =============
    // These methods mirror the same logic from HolidayController
    
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
