package com.iteam.Gestion.Expert.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Postuleoffre {
	 @Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	private Date datepostule;
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private Mission missions;
	private Double matchingPercentage;

	// validé
	 @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
         private  Expert expert;
}
