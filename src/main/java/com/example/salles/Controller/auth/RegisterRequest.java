package com.example.salles.Controller.auth;

import com.example.salles.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String phone;
    private Role role;
    private Boolean enabled;
    private String password;
}
