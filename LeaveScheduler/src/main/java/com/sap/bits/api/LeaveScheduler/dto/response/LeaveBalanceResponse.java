package com.sap.bits.api.LeaveScheduler.dto.response;

import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceResponse {
    private Long id;
    private Long userId;
    private LeaveType leaveType;
    private Float balance;
    private Float used;
    private Integer year;
    private String leaveTypeName;
}