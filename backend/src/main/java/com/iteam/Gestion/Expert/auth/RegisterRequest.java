package com.iteam.Gestion.Expert.auth;

import com.iteam.Gestion.Expert.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long  id;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private Role role;
}
