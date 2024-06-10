package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Diplomesdto;

import java.util.List;

public interface DiplomesService {

    void deleteDiplomes(Long id);
    List<Diplomesdto> listeallDiplomes ();

    Diplomesdto updateDiplomes(Diplomesdto diplomesdto);

    Diplomesdto findByIddiplomes(Long id);
    Diplomesdto addDiplomes(Diplomesdto diplomesdto);
}
