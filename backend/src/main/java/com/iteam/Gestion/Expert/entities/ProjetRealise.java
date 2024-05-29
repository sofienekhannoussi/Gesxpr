package com.iteam.Gestion.Expert.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class ProjetRealise {
	 @Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	 private String title; //intutulé poste
	    private String description;//texte riche : details missions
	    private Date dateDebut;
	    private Date dateFin;
	    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private Expert expert;
}
