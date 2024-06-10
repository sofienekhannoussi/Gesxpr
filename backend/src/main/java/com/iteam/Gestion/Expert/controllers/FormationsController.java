package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.Formationsdto;
import com.iteam.Gestion.Expert.services.FormationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/formations")
public class FormationsController {

    @Autowired
    private FormationsService formationsService;

    @PostMapping("/add")
    public ResponseEntity<Formationsdto> createFormation(@RequestBody Formationsdto formationsdto) {
        Formationsdto createdFormation = formationsService.createFormation(formationsdto);
        return ResponseEntity.ok(createdFormation);
    }

    @PutMapping("/update")
    public Formationsdto updateFormations(@RequestBody Formationsdto formationsdto) {
        Formationsdto updatedFormation = formationsService.updateFormation( formationsdto);
        return formationsService.updateFormation(updatedFormation);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationsService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByIdFormation/{id}")
    public ResponseEntity<Formationsdto> getFormationById(@PathVariable Long id) {
        Formationsdto formation = formationsService.getFormationById(id);
        return ResponseEntity.ok(formation);
    }

    @GetMapping("/listeall")
    public ResponseEntity<List<Formationsdto>> getAllFormations() {
        List<Formationsdto> formations = formationsService.getAllFormations();
        return ResponseEntity.ok(formations);
    }
}

