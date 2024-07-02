package com.iteam.Gestion.Expert.dto;


import com.iteam.Gestion.Expert.entities.EvaluationExpert;
import com.iteam.Gestion.Expert.entities.Expert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationExpertdto {

    private Long  id;
    private Double scoreTestOral;
    private Double scoreTestPsychotechnique;
    private Double scoreTestTechnique;
    private Double scoreTestProfil;
    private Long idexpert;
    private Long idresponsablesoci;
    
    public static EvaluationExpert toEntity(EvaluationExpertdto request)
    {
        return EvaluationExpert.builder()
                .id(request.getId())
                .scoreTestOral(request.getScoreTestOral())
                .scoreTestPsychotechnique(request.getScoreTestPsychotechnique())
                .scoreTestTechnique(request.getScoreTestTechnique())
                .scoreTestProfil(request.getScoreTestProfil())
                .build();


    }
    public static EvaluationExpertdto fromEntity(EvaluationExpert request)
    {
        return EvaluationExpertdto.builder()
                .id(request.getId())
                .scoreTestOral(request.getScoreTestOral())
                .scoreTestPsychotechnique(request.getScoreTestTechnique())
                .scoreTestTechnique(request.getScoreTestTechnique())
                .scoreTestProfil(request.getScoreTestProfil())
                .build();


    }
}
