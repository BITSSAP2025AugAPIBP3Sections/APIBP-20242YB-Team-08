package com.sap.bits.api.LeaveScheduler.repository;

import com.sap.bits.api.LeaveScheduler.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
