package com.iteam.Gestion.Expert.entities;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Expert")
public class Expert extends User {
	private String adresse;
    private String biography;
    private String specialite;
    private String cv;



    //les projets realises
    @OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL,mappedBy="expert")
    private List<ProjetRealise> projetRealises;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "expert", cascade = CascadeType.ALL)
    private    Set<Competences> competences =new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "expert", cascade = CascadeType.ALL)
    private    Set<Diplomes> diplomes =new HashSet<>();

//// validé
    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private Mission missions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "expert", cascade = CascadeType.ALL)
    private    Set<Postuleoffre> postuleoffres =new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private EvaluationExpert evaluationExpert;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contrat contrat;
}
