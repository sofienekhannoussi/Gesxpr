package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.services.CompetencesService;
import com.iteam.Gestion.Expert.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/competence")
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetencesService competencesServiceService;

    @DeleteMapping("/delete/{id}")

    public void deleteCompetences(@PathVariable("id") Long id) {
        competencesServiceService.deleteCompetences(id);
    }

    @GetMapping("/listeall")


    public List<Competencesdto> listeallCompetences() {
        return competencesServiceService.listeallCompetences();
    }

    @PostMapping("/update")

    public Competencesdto updateCompetences(Competencesdto competencesdto) {
        return competencesServiceService.updateCompetences(competencesdto);
    }

    @GetMapping("/findByIdCompetence/{id}")

    public Competencesdto findByIdcompetences(Long id) {
        return competencesServiceService.findByIdcompetences(id);
    }
    @PostMapping("/add")

    public Competences addCompetences(Competencesdto competencesdto) {
        return competencesServiceService.addCompetences(competencesdto);
    }
}
