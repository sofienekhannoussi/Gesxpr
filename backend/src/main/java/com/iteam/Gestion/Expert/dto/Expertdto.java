package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.auth.RegisterRequest;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Expertdto extends RegisterRequest {
	private String adresse;
    private String biography;

    public static Expert toEntity(Expertdto request)
    {
        return Expert.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .biography(request.getBiography())
                .build();


    }
    public static Expertdto fromEntity(Expert request)
    {
        return Expertdto.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(request.getRole())
                .adresse(request.getAdresse())
                .biography(request.getBiography())
                .build();


    }
}
