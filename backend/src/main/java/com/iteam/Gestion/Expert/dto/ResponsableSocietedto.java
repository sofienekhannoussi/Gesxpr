package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.auth.RegisterRequest;
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
public class ResponsableSocietedto extends RegisterRequest {
	 private String presentationsociete;

    public static ResponsableSociete toEntity(ResponsableSocietedto request)
    {
    	
        return ResponsableSociete.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .presentationsociete(request.getPresentationsociete())
                .avatar(request.getAvatar())
                .build();


    }
    public static ResponsableSocietedto fromEntity(ResponsableSociete request)
    {
        return ResponsableSocietedto.builder()
        		.id(request.getId())
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .presentationsociete(request.getPresentationsociete())
                .avatar(request.getAvatar())
                .build();


    }

}
