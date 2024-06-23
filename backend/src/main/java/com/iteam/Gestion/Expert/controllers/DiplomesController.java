package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.services.DiplomesService;
import com.iteam.Gestion.Expert.services.ProjetRealiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diplomes")
@RequiredArgsConstructor
public class DiplomesController {

    private final DiplomesService diplomesService;



    @DeleteMapping("/delete/{id}")

    public void deleteDiplomes(@PathVariable("id") Long id) {
        diplomesService.deleteDiplomes(id);
    }

    @GetMapping("/listeall")

    public List<Diplomesdto> listeallDiplomes() {
        return diplomesService.listeallDiplomes();
    }

    @GetMapping("/listealldplbyexpert/{id}")

    public List<Diplomesdto> listeallDiplomes(@PathVariable("id")Long id) {
        return diplomesService.listDiplomesByidexpert(id);
    }

    @GetMapping("/findByIdDiplome/{id}")

    public Diplomesdto findByIdDiplome(@PathVariable("id") Long id) {
        return diplomesService.findByIddiplomes(id);
    }

    @PostMapping("/update")

    public Diplomesdto updateDiplomes(@RequestBody Diplomesdto diplomesdto) {
        return diplomesService.updateDiplomes(diplomesdto);
    }

    @PostMapping("/add")

    public Diplomesdto addDiplomes(@RequestBody Diplomesdto diplomesdto) {
        return diplomesService.addDiplomes(diplomesdto);
    }
}
