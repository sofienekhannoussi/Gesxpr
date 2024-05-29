package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Postuleoffre;

import java.util.List;
import java.util.Optional;

public interface PostuleoffreService {

    void deleteOffre(Long id);
    List<Postuleoffredto> listeallOffre ();
    Optional<Postuleoffre> findByIdOffre(Long id);
    Postuleoffre addOffre(Postuleoffredto postuleoffredto);
}
