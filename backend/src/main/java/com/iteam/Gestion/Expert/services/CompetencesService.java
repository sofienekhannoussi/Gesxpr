package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Diplomesdto;


import java.util.List;
import java.util.Optional;

public interface CompetencesService {

    void deleteCompetences(Long id);
    List<Competencesdto> listeallCompetences ();

    List<Competencesdto> listCompetencesByidexpert (Long id);


    Competencesdto updateCompetences(Competencesdto competencesdto);

    Competencesdto findByIdcompetences(Long id);
     Competencesdto addCompetences(Competencesdto competencesdto);
}
