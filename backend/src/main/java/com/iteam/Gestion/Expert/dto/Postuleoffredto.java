package com.iteam.Gestion.Expert.dto;


import com.iteam.Gestion.Expert.entities.Postuleoffre;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Postuleoffredto {

    private Long id;
    private Date datepostule;


    public static Postuleoffre toEntity(Postuleoffredto request) {
        return Postuleoffre.builder()
                .id(request.getId())
                .datepostule(request.getDatepostule())
                .build();
    }
    public static Postuleoffredto fromEntity(Postuleoffre request) {
        return Postuleoffredto.builder()
                .id(request.getId())
                .datepostule(request.getDatepostule())
                .build();
    }



}
