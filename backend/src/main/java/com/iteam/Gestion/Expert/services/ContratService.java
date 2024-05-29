package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.entities.Contrat;

import java.util.List;
import java.util.Optional;

public interface ContratService {

    void deleteContrat(Long id);
    List<Contratdto> listeallContrat ();
    Optional<Contrat> findByIdContrat(Long id);
    Contrat addContrat(Contratdto contratdto);
}
