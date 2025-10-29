package com.sap.bits.api.LeaveScheduler.dto.request;

import java.util.Set;

import com.sap.bits.api.LeaveScheduler.model.enums.LeaveType;
import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeavePolicyRequest {
    private Long id;

    @NotNull
    private LeaveType leaveType;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    private Float annualCredit;

    private Float maxAccumulation;

    private Boolean isCarryForward;

    private Integer minDuration;

    private Integer maxDuration;

    private Integer noticeRequired;

    private Set<UserRole> applicableRoles;

    private Boolean isActive;
}