package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.ProjetRealise;
import com.iteam.Gestion.Expert.services.ProjetRealiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/projet")
@RequiredArgsConstructor

public class ProjetRealiseController {

    private final ProjetRealiseService projetRealiseService;


    @DeleteMapping("/delete/{id}")

    public void deleteProjet(Long id) {
        projetRealiseService.deleteProjet(id);
    }

    public List<ProjetRealisedto> listeallProjet() {
        return projetRealiseService.listeallProjet();
    }

    public Optional<ProjetRealise> findByIdProjet(Long id) {
        return projetRealiseService.findByIdProjet(id);
    }

    @PostMapping("/save")

    public ProjetRealise addOffre(ProjetRealisedto projetRealisedto) {
        return projetRealiseService.addOffre(projetRealisedto);
    }
}
