package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Expert;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProfilExpertdto {

    private String adresse;
    private String biography;
    private Long id;
    private String fullname;
    private String email;
    private String phone;
    private String avatar;


    public static Expert toEntity(ProfilExpertdto request) {
        return Expert.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .adresse(request.getAdresse())
                .biography(request.getBiography())
                .avatar(request.getAvatar())
                .build();


    }
    public static ProfilExpertdto FromEntity(Expert request) {
        return ProfilExpertdto .builder()
                .id(request.getId())
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .adresse(request.getAdresse())
                .biography(request.getBiography())
                .avatar(request.getAvatar())
                .build();


    }


}