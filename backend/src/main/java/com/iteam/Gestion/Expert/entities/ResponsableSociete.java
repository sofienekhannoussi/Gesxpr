package com.iteam.Gestion.Expert.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Respsoci")


public class ResponsableSociete extends User {
    private String presentationsociete;
    private String adresse;
    // validé
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsableSociete", cascade = CascadeType.ALL)
    Set<Mission> missions=new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsableSociete", cascade = CascadeType.ALL)
    Set<EvaluationExpert> evaluationExpert=new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsable", cascade = CascadeType.ALL)
    private List <Societe> societes=new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Contrat> contrats=new ArrayList<>();
}
