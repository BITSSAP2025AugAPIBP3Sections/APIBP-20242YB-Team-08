package com.sap.bits.api.LeaveScheduler.controller.graphql;

import com.sap.bits.api.LeaveScheduler.model.Notification;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.model.enums.NotificationType;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NotificationGraphQLController {

    private final List<Notification> mockNotifications = new ArrayList<>();

    public NotificationGraphQLController() {

        User user = new User();
        user.setId(1L);
        user.setEmail("demo.user@example.com");

        Notification n1 = new Notification(
                1L,
                user,
                "Leave Approved",
                "Your leave request has been approved.",
                NotificationType.LEAVE_APPROVED,
                101L,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Notification n2 = new Notification(
                2L,
                user,
                "Leave Rejected",
                "Your leave request has been rejected.",
                NotificationType.LEAVE_REJECTED,
                102L,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        mockNotifications.add(n1);
        mockNotifications.add(n2);
    }

    @QueryMapping
    public List<Notification> notifications() {
        return mockNotifications;
    }

    @QueryMapping
    public List<Notification> unreadNotifications() {
        return mockNotifications.stream()
                .filter(n -> !n.getIsRead())
                .toList();
    }

    @QueryMapping
    public Long unreadNotificationCount() {
        return mockNotifications.stream()
                .filter(n -> !n.getIsRead())
                .count();
    }

    @MutationMapping
    public Notification markNotificationAsRead(@Argument Long id) {
        for (Notification n : mockNotifications) {
            if (n.getId().equals(id)) {
                n.setIsRead(true);
                n.setUpdatedAt(LocalDateTime.now());
                return n;
            }
        }
        return null; // GraphQL will return null if not found
    }

    @MutationMapping
    public ApiResponse markAllNotificationsAsRead() {
        mockNotifications.forEach(n -> {
            n.setIsRead(true);
            n.setUpdatedAt(LocalDateTime.now());
        });
        return new ApiResponse(true, "All notifications marked as read");
    }
}