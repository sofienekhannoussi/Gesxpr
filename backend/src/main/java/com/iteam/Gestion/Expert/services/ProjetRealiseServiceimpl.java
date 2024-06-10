package com.iteam.Gestion.Expert.services;


import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.ProjetRealise;
import com.iteam.Gestion.Expert.reposetories.CompetenceRepository;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.ProjetRealiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetRealiseServiceimpl implements ProjetRealiseService {

    private final ProjetRealiseRepository projetRealiseRepository;

    private final ExpertRepository expertRepository;
    @Override
    public void deleteProjet(Long id) {
        projetRealiseRepository.deleteById(id);


    }

    @Override
    public List<ProjetRealisedto> listeallProjet() {
        return projetRealiseRepository.findAll().stream().map(ProjetRealisedto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProjetRealisedto updatePorjet(ProjetRealisedto projetRealisedto) {
        Optional<Expert> expert = expertRepository.findById(projetRealisedto.getIdexpert());

        ProjetRealise projetRealise = ProjetRealisedto.toEntity(projetRealisedto);
        if (expert.isPresent()) {
            projetRealise.setExpert(expert.get());
            ProjetRealise projetRealisesaved = projetRealiseRepository.save(projetRealise);
            return ProjetRealisedto.fromEntity(projetRealisesaved);
        } else
            throw new RuntimeException("err");
    }


    @Override
    public ProjetRealisedto findByIdProjet(Long id) {

        Optional<ProjetRealise> projetRealise = projetRealiseRepository.findById(id);
        if (projetRealise.isPresent()) {
            ProjetRealisedto projetRealisedto = ProjetRealisedto.fromEntity(projetRealise.get());
            return projetRealisedto;
        } else

            return null;

    }

    @Override
    public ProjetRealisedto addProjet(ProjetRealisedto projetRealisedto) {
        Optional<Expert> expert = expertRepository.findById(projetRealisedto.getIdexpert());

        ProjetRealise projetRealise = ProjetRealisedto.toEntity(projetRealisedto);
        if (expert.isPresent()) {
            projetRealise.setExpert(expert.get());
            ProjetRealise projetRealisesaved = projetRealiseRepository.save(projetRealise);
            return ProjetRealisedto.fromEntity(projetRealisesaved);
        } else
            throw new RuntimeException("err");
    }
}
