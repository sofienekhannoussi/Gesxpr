package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.ProjetRealise;

import java.util.List;
import java.util.Optional;

public interface ProjetRealiseService {

    void deleteProjet(Long id);
    List<ProjetRealisedto> listeallProjet ();

    List<ProjetRealisedto> listProjetByidexpert (Long id);


    ProjetRealisedto updatePorjet(ProjetRealisedto projetRealisedto);

    ProjetRealisedto findByIdProjet(Long id);
    ProjetRealisedto addProjet(ProjetRealisedto projetRealisedto);
}
