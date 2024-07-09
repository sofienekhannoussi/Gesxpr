package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.dto.EvaluationExpertdto;
import com.iteam.Gestion.Expert.entities.EvaluationExpert;
import com.iteam.Gestion.Expert.services.EvaluationExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/evaluation")
@RequiredArgsConstructor
public class EvaluationExpertController {

    private final EvaluationExpertService evaluationExpertService;

    @DeleteMapping("/delete/{id}")
    public void deleteEvaluation(@PathVariable("id") Long id) {
        evaluationExpertService.deleteEvaluation(id);
    }


    @GetMapping("/listeall")


    public List<EvaluationExpertdto> listeallEvaluation() {
        return evaluationExpertService.listeallEvaluation();
    }

    @GetMapping("/listeallbyresp/{id}")


    public List<EvaluationExpertdto> listeallEvaluationByRESP(@PathVariable("id") Long id) {
        return evaluationExpertService.listeallEvaluationbyResp(id);
    }

    @GetMapping("/findByIdEvaluation/{id}")
    public EvaluationExpertdto findByIdEvaluation(@PathVariable("id") Long id) {return evaluationExpertService.findByIdEvaluation(id);
    }
    @PostMapping("/add")

    public EvaluationExpertdto addEvaluation(@RequestBody EvaluationExpertdto evaluationExpertdto) {
        return evaluationExpertService.addEvaluation(evaluationExpertdto);
    }
    @PostMapping("/update")

    public EvaluationExpertdto updateEvaluation(@RequestBody EvaluationExpertdto evaluationExpertdto) {
        return evaluationExpertService.updateEvaluation(evaluationExpertdto);
    }

}