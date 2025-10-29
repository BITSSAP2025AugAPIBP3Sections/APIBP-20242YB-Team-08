package com.sap.bits.api.LeaveScheduler.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String userName;
    private String title;
    private String eventType;
}