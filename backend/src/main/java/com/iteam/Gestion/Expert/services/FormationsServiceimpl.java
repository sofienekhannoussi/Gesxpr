package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Formationsdto;
import com.iteam.Gestion.Expert.entities.Formations;
import com.iteam.Gestion.Expert.reposetories.FormationsRepository;
import com.iteam.Gestion.Expert.services.FormationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationsServiceimpl implements FormationsService {
    @Autowired
    private FormationsRepository formationsRepository;

    @Override
    public Formationsdto createFormation(Formationsdto formationsdto) {
        Formations formation = Formationsdto.toEntity(formationsdto);
        Formations savedFormation = formationsRepository.save(formation);
        return Formationsdto.fromEntity(savedFormation);
    }

    @Override
    public Formationsdto updateFormation( Formationsdto formationsdto) {
        Formations existingFormation = formationsRepository.findById(formationsdto.getIdexpert()).orElseThrow(() -> new RuntimeException("Formation not found"));
        existingFormation.setTitre(formationsdto.getTitre());
        existingFormation.setIsActive(formationsdto.getIsActive()); // Set other fields as needed Formations
        Formations updatedFormation = formationsRepository.save(existingFormation);
        return Formationsdto.fromEntity(updatedFormation);
    }

    @Override
    public void deleteFormation(Long id) {
        formationsRepository.deleteById(id);
    }

    @Override
    public Formationsdto getFormationById(Long id) {
        Formations formation = formationsRepository.findById(id).orElseThrow(() -> new RuntimeException("Formation not found"));
        return Formationsdto.fromEntity(formation);
    }

    @Override
    public List<Formationsdto> getAllFormations() {
        return formationsRepository.findAll().stream().map(Formationsdto::fromEntity).collect(Collectors.toList());
    }
}

