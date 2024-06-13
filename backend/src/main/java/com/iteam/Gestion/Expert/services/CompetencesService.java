package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;


import java.util.List;
import java.util.Optional;

public interface CompetencesService {

    void deleteCompetences(Long id);
    List<Competencesdto> listeallCompetences ();

    Competencesdto updateCompetences(Competencesdto competencesdto);

    Competencesdto findByIdcompetences(Long id);
     Competencesdto addCompetences(Competencesdto competencesdto);
}
