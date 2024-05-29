package com.iteam.Gestion.Expert.entities;

import java.util.Date;

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
    private Long  reference;
    private String title; //intutulé poste
    private String description;//texte riche : details missions
    private Date dateDebut;
    private Date dateFin;
    private String statut; //en cours terminé en attente
    private Boolean isActive;
    private String typeContrat;
    private String typeLieu;//a distance ou sur site
    private String typeTravail; //fulltime mid time
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private ResponsableSociete responsableSociete;
    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Expert expert;
    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Postuleoffre postuleoffre;
    

}
