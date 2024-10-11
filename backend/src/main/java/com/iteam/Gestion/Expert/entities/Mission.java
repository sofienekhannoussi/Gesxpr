package com.iteam.Gestion.Expert.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String title; //intutulé poste
    @Column(length = 4000)
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
    //validé
    @OneToOne(fetch = FetchType.LAZY , mappedBy = "missions")
    private  Expert expert;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "missions")
    private List<Postuleoffre> postuleoffre;
    

}
