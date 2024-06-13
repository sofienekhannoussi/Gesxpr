package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Diplomes;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diplomesdto {

    private Long  id;
    private String title;

    private String description;
    private String dateDebut;
    private String dateFin;
    private String universityName;
    private Long idexpert;



    public static Diplomes toEntity(Diplomesdto request) {
        return Diplomes.builder()
           .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .universityName(request.getUniversityName())
                .build();
    }
    public static Diplomesdto fromEntity(Diplomes request) {
        return Diplomesdto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .universityName(request.getUniversityName())
                .build();

    }



}


