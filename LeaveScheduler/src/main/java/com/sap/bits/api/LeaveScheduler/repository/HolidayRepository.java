package com.sap.bits.api.LeaveScheduler.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sap.bits.api.LeaveScheduler.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT h FROM Holiday h WHERE YEAR(h.date) = :year")
    List<Holiday> findByYear(@Param("year") Integer year);

    @Query("SELECT h FROM Holiday h WHERE MONTH(h.date) = :month AND YEAR(h.date) = :year")
    List<Holiday> findByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);

    boolean existsByDate(LocalDate date);

    List<Holiday> findByIsRecurringTrue();
}