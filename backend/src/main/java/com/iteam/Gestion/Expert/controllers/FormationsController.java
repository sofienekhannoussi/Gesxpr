package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Formationsdto;
import com.iteam.Gestion.Expert.services.FormationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationsController {

    @Autowired
    private FormationsService formationsService;

    @PostMapping
    public ResponseEntity<Formationsdto> createFormation(@RequestBody Formationsdto formationsdto) {
        Formationsdto createdFormation = formationsService.createFormation(formationsdto);
        return ResponseEntity.ok(createdFormation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formationsdto> updateFormation(@PathVariable Long id, @RequestBody Formationsdto formationsdto) {
        Formationsdto updatedFormation = formationsService.updateFormation(id, formationsdto);
        return ResponseEntity.ok(updatedFormation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationsService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formationsdto> getFormationById(@PathVariable Long id) {
        Formationsdto formation = formationsService.getFormationById(id);
        return ResponseEntity.ok(formation);
    }

    @GetMapping
    public ResponseEntity<List<Formationsdto>> getAllFormations() {
        List<Formationsdto> formations = formationsService.getAllFormations();
        return ResponseEntity.ok(formations);
    }
}

