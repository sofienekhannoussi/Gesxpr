package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Contrat;
import lombok.*;

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
    private Date duree;
    private String pdfContrat;

    public static Contrat toEntity(Contratdto request) {
        return Contrat.builder()
                .id(request.getId())
                .reference(request.getReference())
                .type(request.getType())
                .duree(request.getDuree())
                .pdfContrat(request.getPdfContrat())
                .build();
    }
    public static Contratdto fromEntity(Contrat request) {
        return Contratdto.builder()
                .id(request.getId())
                .reference(request.getReference())
                .type(request.getType())
                .duree(request.getDuree())
                .pdfContrat(request.getPdfContrat())
                .build();
    }




}
