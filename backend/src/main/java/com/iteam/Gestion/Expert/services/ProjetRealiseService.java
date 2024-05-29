package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.ProjetRealise;

import java.util.List;
import java.util.Optional;

public interface ProjetRealiseService {

    void deleteProjet(Long id);
    List<ProjetRealisedto> listeallProjet ();
    Optional<ProjetRealise> findByIdProjet(Long id);
    ProjetRealise addOffre(ProjetRealisedto projetRealisedto);
}
