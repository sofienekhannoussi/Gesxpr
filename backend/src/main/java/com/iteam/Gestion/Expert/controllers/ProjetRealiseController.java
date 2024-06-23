package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.services.ProjetRealiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projet")
@RequiredArgsConstructor

public class ProjetRealiseController {

    private final ProjetRealiseService projetRealiseService;


    @DeleteMapping("/delete/{id}")

    public void deleteProjet(@PathVariable("id") Long id) {
        projetRealiseService.deleteProjet(id);
    }

    @GetMapping("/listeall")

    public List<ProjetRealisedto> listeallProjet() {
        return projetRealiseService.listeallProjet();
    }


    @GetMapping("/listeallprojetbyexpert/{id}")

    public List<ProjetRealisedto> listeallDiplomes(@PathVariable("id")Long id) {
        return projetRealiseService.listProjetByidexpert(id);
    }
    @GetMapping("/findByIdProjet/{id}")

    public ProjetRealisedto findByIdProjet(@PathVariable("id") Long id) {
        return projetRealiseService.findByIdProjet(id);
    }

    @PostMapping("/update")

    public ProjetRealisedto updateProjet(@RequestBody ProjetRealisedto projetRealisedto) {
        return projetRealiseService.updatePorjet(projetRealisedto);
    }

    @PostMapping("/add")

    public ProjetRealisedto addProjet(@RequestBody ProjetRealisedto projetRealisedto) {
        return projetRealiseService.addProjet(projetRealisedto);
    }
}
