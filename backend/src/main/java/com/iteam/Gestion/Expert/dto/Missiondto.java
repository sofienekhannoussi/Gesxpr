package com.iteam.Gestion.Expert.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


import java.util.Date;

import com.iteam.Gestion.Expert.entities.Mission;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Missiondto {


    private Long  id;
    private String title; //intutulé poste
    private String description;//texte riche : details missions
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    private String statut; //en cours terminé en attente
    private Boolean isActive;
    private String typeContrat;
    private String typeLieu;//a distance ou sur site
    private Long idresponsablesoci;
    public static Mission toEntity(Missiondto request) {
	    return Mission.builder()
	        .id(request.getId())
	        .dateDebut(request.getDateDebut())
	        .dateFin(request.getDateFin())
	        .statut(request.getStatut())
	        .title(request.getTitle())
	        .description(request.getDescription())  
	        .isActive(request.getIsActive())
	        .typeContrat(request.getTypeContrat())
	        .typeLieu(request.getTypeLieu())
	        .build();
	  }
	public static Missiondto fromEntity(Mission request) {
	    return Missiondto.builder()
	    		.id(request.getId())
		        .dateDebut(request.getDateDebut())
		        .dateFin(request.getDateFin())
		        .statut(request.getStatut())

		        .title(request.getTitle())
		        .description(request.getDescription())  
		        .isActive(request.getIsActive())
		        .typeContrat(request.getTypeContrat())
		        .typeLieu(request.getTypeLieu())
	        .build();
	  }

    

}
