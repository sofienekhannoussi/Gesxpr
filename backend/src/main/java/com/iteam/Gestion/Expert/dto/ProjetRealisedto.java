package com.iteam.Gestion.Expert.dto;


import com.iteam.Gestion.Expert.entities.ProjetRealise;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetRealisedto {

    private Long id;
    private String title; //intutulé poste
    private String description;//texte riche : details missions
    private Date dateDebut;
    private Date dateFin;

    public static ProjetRealise toEntity(ProjetRealisedto request) {
        return ProjetRealise.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .build();
    }
    public static ProjetRealisedto fromEntity(ProjetRealise request) {
        return ProjetRealisedto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .build();
    }





}
