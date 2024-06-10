package com.iteam.Gestion.Expert.dto;
import com.iteam.Gestion.Expert.entities.Formations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Formationsdto {
    private Long id;
    private String titre;
    private Boolean isActive;
    private Long idexpert;
    public static Formations toEntity(Formationsdto request) {
        return Formations.builder()
                .id(request.getId())
                .isActive(request.getIsActive())
                .titre(request.getTitre())
                .build();
    }
    public static Formationsdto fromEntity(Formations request) {
        return Formationsdto.builder()
                .id(request.getId())
                .isActive(request.getIsActive())
                .titre(request.getTitre())
                .build();
    }

}

