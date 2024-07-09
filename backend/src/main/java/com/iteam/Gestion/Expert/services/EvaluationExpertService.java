package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.EvaluationExpertdto;
import com.iteam.Gestion.Expert.entities.EvaluationExpert;

import java.util.List;
import java.util.Optional;

public interface EvaluationExpertService {

    void deleteEvaluation(Long id);
    List<EvaluationExpertdto> listeallEvaluation ();
    List<EvaluationExpertdto> listeallEvaluationbyResp (Long id);


    EvaluationExpertdto findByIdEvaluation(Long id);

    EvaluationExpertdto updateEvaluation(EvaluationExpertdto evaluationExpertdto);

    EvaluationExpertdto addEvaluation(EvaluationExpertdto evaluationExpertdto);


}