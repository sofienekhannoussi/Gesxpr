package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Formationsdto;
import java.util.List;

public interface FormationsService {
    Formationsdto createFormation(Formationsdto formationsdto);

    Formationsdto updateFormation(Formationsdto formationsdto);

    void deleteFormation(Long id);

    Formationsdto getFormationById(Long id);

    List<Formationsdto> getAllFormations();
}

