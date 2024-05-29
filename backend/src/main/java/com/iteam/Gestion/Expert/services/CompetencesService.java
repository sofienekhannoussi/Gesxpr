package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Mission;

import java.util.List;
import java.util.Optional;

public interface CompetencesService {

    void deleteCompetences(Long id);
    List<Competencesdto> listeallCompetences ();

    Competencesdto updateCompetences(Competencesdto competencesdto);

    Competencesdto findByIdcompetences(Long id);
    Competences addCompetences(Competencesdto competencesdto);
}
