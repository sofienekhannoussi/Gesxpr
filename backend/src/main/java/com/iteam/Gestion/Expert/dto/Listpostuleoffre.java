package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Listpostuleoffre {
    private Long id;
    private Date datepostule;
    private Missiondto missionsdto;
    private Double matchingPercentage;
    private Expertdto expertdto;
    public static Postuleoffre toEntity(Listpostuleoffre request) {
        return Postuleoffre.builder()
                .id(request.getId())
                .matchingPercentage(request.getMatchingPercentage())
                .datepostule(request.getDatepostule())
                .expert(Expertdto.toEntity(request.getExpertdto()))
                .missions(Missiondto.toEntity(request.getMissionsdto()))
                .build();
    }
    public static Listpostuleoffre fromEntity(Postuleoffre request) {
        return Listpostuleoffre.builder()
                .id(request.getId())
                .datepostule(request.getDatepostule())
                .matchingPercentage(request.getMatchingPercentage())
                .missionsdto(Missiondto.fromEntity(request.getMissions()))
                .expertdto(Expertdto.fromEntity(request.getExpert()))
                .build();
    }
}
