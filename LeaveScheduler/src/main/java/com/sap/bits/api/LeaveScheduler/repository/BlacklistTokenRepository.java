package com.sap.bits.api.LeaveScheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.bits.api.LeaveScheduler.model.BlacklistedToken;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);
}