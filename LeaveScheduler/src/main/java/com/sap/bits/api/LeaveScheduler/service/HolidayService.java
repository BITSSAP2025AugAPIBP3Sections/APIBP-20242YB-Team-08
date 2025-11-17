package com.sap.bits.api.LeaveScheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.exception.BadRequestException;
import com.sap.bits.api.LeaveScheduler.exception.ResourceNotFoundException;
import com.sap.bits.api.LeaveScheduler.model.AuditLog;
import com.sap.bits.api.LeaveScheduler.model.Holiday;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.repository.AuditLogRepository;
import com.sap.bits.api.LeaveScheduler.repository.HolidayRepository;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditLogRepository auditLogRepository;

    private void logAdminAction(String action, String details) {
        User currentUser = userService.getCurrentUser();
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDetails(details);
        log.setAdminId(currentUser.getId());
        log.setActionTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    /**
     * Create a new holiday
     */
    @Transactional
    public Holiday createHoliday(Holiday holiday) {
        // Check if holiday already exists on this date
        if (holidayRepository.existsByDate(holiday.getDate())) {
            throw new BadRequestException("A holiday already exists on this date");
        }

        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUpdatedAt(LocalDateTime.now());
        Holiday savedHoliday = holidayRepository.save(holiday);

        logAdminAction("CREATE_HOLIDAY", "Holiday created: " + savedHoliday.getName());
        return savedHoliday;
    }

    @Transactional
    public List<Holiday> createHolidays(List<Holiday> holidays) {
        List<Holiday> created = new ArrayList<>();
        Set<LocalDate> seenDates = new HashSet<>();
        for (Holiday holiday : holidays) {
            // Check for duplicate dates in the same request
            if (!seenDates.add(holiday.getDate())) {
                throw new BadRequestException("Duplicate holiday date in request: " + holiday.getDate());
            }
        }
        for (Holiday holiday : holidays) {
            created.add(createHoliday(holiday)); // reuse your single create logic (with validation)
        }
        return created;
    }

    /**
     * Get all holidays
     */
    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    /**
     * Get holiday by ID
     */
    public Holiday getHolidayById(Long id) {
        return holidayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holiday", "id", id));
    }

    /**
     * Get holidays by year
     */
    public List<Holiday> getHolidaysByYear(Integer year) {
        List<Holiday> holidays = holidayRepository.findByYear(year);
        // Add recurring holidays from other years
        List<Holiday> recurring = holidayRepository.findByIsRecurringTrue();
        for (Holiday h : recurring) {
            if (h.getDate() != null && h.getDate().getYear() != year) {
                // Create a copy with the requested year
                Holiday recurringCopy = new Holiday(
                        h.getId(),
                        h.getName(),
                        h.getDate().withYear(year),
                        h.getType(),
                        h.getDescription(),
                        h.getIsRecurring(),
                        h.getCreatedAt(),
                        h.getUpdatedAt());
                holidays.add(recurringCopy);
            }
        }
        return holidays;
    }

    /**
     * Get holidays by month and year
     */
    public List<Holiday> getHolidaysByMonthAndYear(Integer month, Integer year) {
        List<Holiday> holidays = holidayRepository.findByMonthAndYear(month, year);
        // Add recurring holidays from other years
        List<Holiday> recurring = holidayRepository.findByIsRecurringTrue();
        for (Holiday h : recurring) {
            if (h.getDate() != null && h.getDate().getMonthValue() == month && h.getDate().getYear() != year) {
                // Create a copy with the requested year
                Holiday recurringCopy = new Holiday(
                        h.getId(),
                        h.getName(),
                        h.getDate().withYear(year),
                        h.getType(),
                        h.getDescription(),
                        h.getIsRecurring(),
                        h.getCreatedAt(),
                        h.getUpdatedAt());
                holidays.add(recurringCopy);
            }
        }
        return holidays;
    }

    /**
     * Get holidays between dates
     */
    public List<Holiday> getHolidaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        return holidayRepository.findByDateBetween(startDate, endDate);
    }

    /**
     * Update a holiday
     */
    @Transactional
    public Holiday updateHoliday(Long id, Holiday holidayDetails) {
        Holiday holiday = getHolidayById(id);

        // Check if the new date conflicts with existing holidays
        if (!holiday.getDate().isEqual(holidayDetails.getDate()) &&
                holidayRepository.existsByDate(holidayDetails.getDate())) {
            throw new BadRequestException("A holiday already exists on this date");
        }

        holiday.setName(holidayDetails.getName());
        holiday.setDate(holidayDetails.getDate());
        holiday.setDescription(holidayDetails.getDescription());
        holiday.setUpdatedAt(LocalDateTime.now());

        Holiday updatedHoliday = holidayRepository.save(holiday);

        logAdminAction("UPDATE_HOLIDAY", "Holiday updated: " + updatedHoliday.getName());
        return updatedHoliday;
    }

    /**
     * Delete a holiday
     */
    @Transactional
    public ApiResponse deleteHoliday(Long id) {
        Holiday holiday = getHolidayById(id);
        logAdminAction("DELETE_HOLIDAY", "Holiday deleted: " + holiday.getName());
        holidayRepository.delete(holiday);
        return new ApiResponse(true, "Holiday deleted successfully");
    }

    /**
     * Check if a date is a holiday
     */
    public boolean isHoliday(LocalDate date) {
        return holidayRepository.existsByDate(date);
    }
}