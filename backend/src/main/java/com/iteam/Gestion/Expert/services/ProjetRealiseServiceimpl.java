package com.iteam.Gestion.Expert.services;


import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.ProjetRealise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetRealiseServiceimpl implements ProjetRealiseService {
    @Override
    public void deleteProjet(Long id) {

    }

    @Override
    public List<ProjetRealisedto> listeallProjet() {
        return null;
    }

    @Override
    public Optional<ProjetRealise> findByIdProjet(Long id) {
        return Optional.empty();
    }

    @Override
    public ProjetRealise addOffre(ProjetRealisedto projetRealisedto) {
        return null;
    }
}
