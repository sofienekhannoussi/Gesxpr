package com.iteam.Gestion.Expert.services;


import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.CompetenceRepository;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetencesServiceimpl implements CompetencesService {

    private final CompetenceRepository competenceRepository;

    private final ExpertRepository expertRepository;

    @Override
    public void deleteCompetences(Long id) {
competenceRepository.deleteById(id);
    }

    @Override
    public List<Competencesdto> listeallCompetences() {

        return competenceRepository.findAll().stream().map(Competencesdto::fromEntity).collect(Collectors.toList());

    }

    @Override
    public Competencesdto updateCompetences(Competencesdto competencesdto) {



        Optional<Competences> competences =competenceRepository.findById(competencesdto.getId());
        System.err.println(competences);
        System.err.println(competencesdto.getId());
        if(competences.isPresent())
        {

            Competences statoupdate =competences.get();
            Competences competences1 = Competencesdto.toEntity(competencesdto);
            statoupdate.setCompetenceName(competences1.getCompetenceName());
            statoupdate.setIsActive(competences1.getIsActive());

            System.err.println("//////////////");
            System.err.println(statoupdate);
            Competences updatecomptences =competenceRepository.save(statoupdate);
            return Competencesdto.fromEntity(updatecomptences);
        }
        else
        {
            throw new RuntimeException("no Skill to update");
        }

    }

    @Override
    public Competencesdto findByIdcompetences(Long id) {



        Optional<Competences> competences = competenceRepository.findById(id);
        if (competences.isPresent()) {
                    Competencesdto competencesdto = Competencesdto.fromEntity(competences.get());
                    return competencesdto;
        } else

            return null;

    }

    @Override
    public Competences addCompetences(Competencesdto competencesdto) {
        Optional<Expert> expert = expertRepository.findById(competencesdto.getIdexpert());

        Competences competences = Competencesdto.toEntity(competencesdto);

        if (expert.isPresent()) {
            competences.setExpert(expert.get());
            Competences competencessaved = competenceRepository.save(competences);
            return competences;
        } else
            throw new RuntimeException("err");
    }
}
