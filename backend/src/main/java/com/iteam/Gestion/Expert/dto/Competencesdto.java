package com.iteam.Gestion.Expert.dto;


import com.iteam.Gestion.Expert.entities.Competences;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competencesdto {

    private Long  id;
    private String competenceName;
    private Boolean isActive;
    private Long idexpert;

    public static Competences toEntity(Competencesdto request) {
        return Competences.builder()
                .id(request.getId())
                .isActive(request.getIsActive())
                .competenceName(request.getCompetenceName())
                .build();
    }
    public static Competencesdto fromEntity(Competences request) {
        return Competencesdto.builder()
                .id(request.getId())
                .isActive(request.getIsActive())
                .competenceName(request.getCompetenceName())
                .build();
    }



}




