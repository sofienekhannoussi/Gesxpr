package com.iteam.Gestion.Expert.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Contrat;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contratdto {

    private Long  id;
    private String reference;
    private String type;
    private String duree;
    private String pdfContrat;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateContrat;
    private Long expertId;
    private Long responsibleId;
    private Long missionId;

    public static Contrat toEntity(Contratdto request) {
        return Contrat.builder()
                .id(request.getId())
                .reference(request.getReference())
                .type(request.getType())
                .duree(request.getDuree())
                .pdfContrat(request.getPdfContrat())
                .dateContrat(request.getDateContrat())
                .build();
    }
    public static Contratdto fromEntity(Contrat request) {
        return Contratdto.builder()
                .id(request.getId())
                .reference(request.getReference())
                .type(request.getType())
                .duree(request.getDuree())
                .pdfContrat(request.getPdfContrat())
                .dateContrat(request.getDateContrat())
                .build();
    }




}
