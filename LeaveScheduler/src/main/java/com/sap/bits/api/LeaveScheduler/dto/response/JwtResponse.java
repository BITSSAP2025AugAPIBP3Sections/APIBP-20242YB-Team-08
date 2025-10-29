package com.sap.bits.api.LeaveScheduler.dto.response;

import java.util.Set;

import com.sap.bits.api.LeaveScheduler.model.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Set<UserRole> roles;

    public JwtResponse(String token, Long id, String username, String fullName, String email, Set<UserRole> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
    }
}