package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Formations;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPostuledto {
    private  Long id;
    private Date datepostule;

    private Missiondto missiondto;
    private Expertdto expertdto;

    public static ListPostuledto fromEntity(Postuleoffre request) {
        return ListPostuledto.builder()
                .id(request.getId())
                .datepostule(request.getDatepostule())
                .missiondto(Missiondto.fromEntity(request.getMissions()))
                .expertdto(Expertdto.fromEntity(request.getExpert()))
                .build();
    }

}
