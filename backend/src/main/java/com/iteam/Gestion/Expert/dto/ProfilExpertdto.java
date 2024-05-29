package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Expert;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProfilExpertdto {

    private String cv;
    private String biography;
    private Long id;
    private String fullname;
    private String email;
    private String phone;


    public static Expert toEntity(ProfilExpertdto request) {
        return Expert.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .cv(request.getCv())
                .biography(request.getBiography())
                .build();


    }
    public static ProfilExpertdto FromEntity(Expert request) {
        return ProfilExpertdto .builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .cv(request.getCv())
                .biography(request.getBiography())
                .build();


    }


}