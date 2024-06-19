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
    private String dateDebut;
    private String dateFin;
    private String nomSociete;
    private Long idexpert;



    public static ProjetRealise toEntity(ProjetRealisedto request) {
        return ProjetRealise.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .nomSociete(request.getNomSociete())
                .build();
    }
    public static ProjetRealisedto fromEntity(ProjetRealise request) {
        return ProjetRealisedto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .nomSociete(request.getNomSociete())
                .build();
    }





}
