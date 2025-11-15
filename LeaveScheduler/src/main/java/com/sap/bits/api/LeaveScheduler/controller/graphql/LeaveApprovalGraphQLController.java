package com.sap.bits.api.LeaveScheduler.controller.graphql;

import com.sap.bits.api.LeaveScheduler.dto.request.LeaveApprovalRequest;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class LeaveApprovalGraphQLController {

    @QueryMapping
    // Authorize only MANAGER role using JWT token
    public List<String> pendingLeaveApplications() {
        return new ArrayList<>(Arrays.asList("Pending 1", "Pending 2", "Pending 3"));
    }

    @QueryMapping
    // Authorize only MANAGER role using JWT token
    public List<String> approvedLeaveApplications() {
        return new ArrayList<>(Arrays.asList("Approved 1", "Approved 2", "Approved 3"));
    }

    @QueryMapping
    // Authorize only MANAGER role using JWT token
    public List<String> rejectedLeaveApplications() {
        return new ArrayList<>(Arrays.asList("Rejected 1", "Rejected 2", "Rejected 3"));
    }

    @MutationMapping
    // Authorize only MANAGER role using JWT token
    public String approveLeave(@Argument Long id, @Argument @Valid LeaveApprovalRequest request) {
        return "Leave application " + id + " approved.";
    }

    @MutationMapping
    // Authorize only MANAGER role using JWT token
    public String rejectLeave(@Argument Long id, @Argument @Valid LeaveApprovalRequest request) {
        return "Leave application " + id + " rejected.";
    }
}