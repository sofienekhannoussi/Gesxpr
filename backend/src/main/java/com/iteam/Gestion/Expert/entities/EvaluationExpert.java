package com.iteam.Gestion.Expert.entities;


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
public class EvaluationExpert {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private Double scoreTestOral;
    private Double scoreTestPsychotechnique;
    private Double scoreTestTechnique;
    private Double scoreTestProfil;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "evaluationExpert", cascade = CascadeType.ALL)
    private Expert expert;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ResponsableSociete responsableSociete;
}
