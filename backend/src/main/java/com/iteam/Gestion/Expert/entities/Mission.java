package com.iteam.Gestion.Expert.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String title; //intutulé poste
    private String description;//texte riche : details missions
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    private String statut; //LISTE :en cours terminé en attente
    private Boolean isActive;
    private String typeContrat;
    private String typeLieu;//a distance ou sur site


    @ManyToOne(fetch = FetchType.LAZY)
    private ResponsableSociete responsableSociete;
    //affecter un expert a une mission
    @OneToOne(fetch = FetchType.LAZY)
    private Expert expert;
    @OneToOne(fetch = FetchType.LAZY)
    private Postuleoffre postuleoffre;
    

}
