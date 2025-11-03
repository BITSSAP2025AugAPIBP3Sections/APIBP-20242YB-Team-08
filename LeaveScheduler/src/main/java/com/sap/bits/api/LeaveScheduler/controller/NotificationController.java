package com.sap.bits.api.LeaveScheduler.controller;

import com.sap.bits.api.LeaveScheduler.model.Notification;
import com.sap.bits.api.LeaveScheduler.dto.response.ApiResponse;
import com.sap.bits.api.LeaveScheduler.model.User;
import com.sap.bits.api.LeaveScheduler.model.enums.NotificationType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification Management", description = "Endpoints for managing notifications")
public class NotificationController {

    // ✅ In-memory mock list
    private final List<Notification> mockNotifications = new ArrayList<>();

    public NotificationController() {
        // ✅ mock user
        User user = new User();
        user.setId(1L);
        user.setEmail("demo.user@example.com");

        // ✅ demo notification 1
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

        // ✅ demo notification 2
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

    @GetMapping
    @Operation(summary = "Get current user's notifications")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Notification>> getCurrentUserNotifications() {
        return ResponseEntity.ok(mockNotifications);
    }

    @GetMapping("/unread")
    @Operation(summary = "Get current user's unread notifications")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Notification>> getUnreadNotifications() {
        List<Notification> unread = mockNotifications.stream()
                .filter(n -> !n.getIsRead())
                .toList();
        return ResponseEntity.ok(unread);
    }

    @GetMapping("/unread-count")
    @Operation(summary = "Get unread notification count")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Long> getUnreadCount() {
        long count = mockNotifications.stream()
                .filter(n -> !n.getIsRead())
                .count();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Mark notification as read")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long id) {
        for (Notification n : mockNotifications) {
            if (n.getId().equals(id)) {
                n.setIsRead(true);
                n.setUpdatedAt(LocalDateTime.now());
                return ResponseEntity.ok(n);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/read-all")
    @Operation(summary = "Mark all notifications as read")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse> markAllRead() {
        mockNotifications.forEach(n -> {
            n.setIsRead(true);
            n.setUpdatedAt(LocalDateTime.now());
        });
        return ResponseEntity.ok(new ApiResponse(true, "All notifications marked as read"));
    }
}