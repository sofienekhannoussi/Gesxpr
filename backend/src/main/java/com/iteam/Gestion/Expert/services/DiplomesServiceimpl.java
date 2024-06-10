package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.ProjetRealisedto;
import com.iteam.Gestion.Expert.entities.*;
import com.iteam.Gestion.Expert.reposetories.DiplomesRepository;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iteam.Gestion.Expert.entities.Expert;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DiplomesServiceimpl implements DiplomesService {

    private final DiplomesRepository diplomesRepository;

    private final ExpertRepository expertRepository;

    @Override
    public void deleteDiplomes(Long id) {
        diplomesRepository.deleteById(id);

    }

    @Override
    public List<Diplomesdto> listeallDiplomes() {
        return diplomesRepository.findAll().stream().map( Diplomesdto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Diplomesdto updateDiplomes(Diplomesdto diplomesdto) {
        Optional<Expert> expert = expertRepository.findById(diplomesdto.getIdexpert());

        Diplomes diplomes = Diplomesdto.toEntity(diplomesdto);
        if (expert.isPresent()) {
            diplomes.setExpert(expert.get());
            Diplomes Diplomessaved = diplomesRepository.save(diplomes);
            return Diplomesdto.fromEntity(diplomes);
        } else
            throw new RuntimeException("err");
    }


    @Override
    public Diplomesdto findByIddiplomes(Long id) {
        Optional<Diplomes> diplomes = diplomesRepository.findById(id);
        if (diplomes.isPresent()) {
            Diplomesdto diplomesdto = Diplomesdto.fromEntity(diplomes.get());
            return diplomesdto;
        } else

            return null;

    }

    @Override
    public Diplomesdto addDiplomes(Diplomesdto diplomesdto) {
        Optional<Expert> expert = expertRepository.findById(diplomesdto.getIdexpert());

        Diplomes diplomes = Diplomesdto.toEntity(diplomesdto);
        if (expert.isPresent()) {
            diplomes.setExpert(expert.get());
            Diplomes Diplomessaved = diplomesRepository.save(diplomes);
            return Diplomesdto.fromEntity(diplomes);
        } else
            throw new RuntimeException("err");
    }
}
