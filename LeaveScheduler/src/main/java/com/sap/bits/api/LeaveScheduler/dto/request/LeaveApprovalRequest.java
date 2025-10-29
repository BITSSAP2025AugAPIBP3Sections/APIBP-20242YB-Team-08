package com.sap.bits.api.LeaveScheduler.dto.request;

import com.sap.bits.api.LeaveScheduler.model.enums.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApprovalRequest {
    @NotNull
    private LeaveStatus status;

    @Size(max = 255)
    private String remarks;
}