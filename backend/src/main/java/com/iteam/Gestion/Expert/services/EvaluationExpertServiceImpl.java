package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.EvaluationExpertdto;
import com.iteam.Gestion.Expert.entities.EvaluationExpert;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.EvaluationExpertRepository;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluationExpertServiceImpl implements EvaluationExpertService {

    private final EvaluationExpertRepository evaluationExpertRepository;
    private final ResponsableSocieteRepository responsableSocieteRepository;

    @Override
    public void deleteEvaluation(Long id) {
        evaluationExpertRepository.deleteById(id);
    }

    @Override
    public List<EvaluationExpertdto> listeallEvaluation() {
        return evaluationExpertRepository.findAll().stream().map(EvaluationExpertdto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<EvaluationExpertdto> listeallEvaluationbyResp(Long id) {
        return evaluationExpertRepository.findById(id).stream().map(EvaluationExpertdto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public EvaluationExpertdto findByIdEvaluation(Long id) {
        Optional<EvaluationExpert> evaluationExpert = evaluationExpertRepository.findById(id);
        if (evaluationExpert.isPresent()) {
            EvaluationExpertdto evaluationExpertdto = EvaluationExpertdto.fromEntity(evaluationExpert.get());
            return evaluationExpertdto;
        } else

            return null;

    }



    @Override
    public EvaluationExpertdto updateEvaluation(EvaluationExpertdto evaluationExpertdto) {


        Optional<EvaluationExpert> evaluationExpert = evaluationExpertRepository.findById(evaluationExpertdto.getId());
        if(evaluationExpert.isPresent())
        {

            EvaluationExpert statoupdate =evaluationExpert.get();
            EvaluationExpert evaluationExpert1 = EvaluationExpertdto.toEntity(evaluationExpertdto);



            EvaluationExpert updateevaluation = evaluationExpertRepository.save(statoupdate);
            return EvaluationExpertdto.fromEntity(updateevaluation);
        }
        else
        {
            throw new RuntimeException("no Evaluation to update");
        }

    }


    @Override
    public EvaluationExpertdto addEvaluation(EvaluationExpertdto evaluationExpertdto) {


        Optional<ResponsableSociete> responsableSociete = responsableSocieteRepository.findById(evaluationExpertdto.getIdresponsablesoci());

        EvaluationExpert evaluationExpert = EvaluationExpertdto.toEntity(evaluationExpertdto);
        if (responsableSociete.isPresent()) {
            evaluationExpert.setResponsableSociete(responsableSociete.get());
            EvaluationExpert evaluationaved = evaluationExpertRepository.save(evaluationExpert);
            return EvaluationExpertdto.fromEntity(evaluationaved);
        } else
            throw new RuntimeException("err");
    }
}