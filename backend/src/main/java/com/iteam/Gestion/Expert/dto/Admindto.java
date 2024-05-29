package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.auth.RegisterRequest;
import com.iteam.Gestion.Expert.entities.Admin;
import com.iteam.Gestion.Expert.entities.Expert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class Admindto extends RegisterRequest {
    public static Admin toEntity(Admindto request)
    {
        return Admin.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .build();


    }
    public static Admindto fromEntity(Admin request)
    {
        return Admindto.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .build();


    }
}
