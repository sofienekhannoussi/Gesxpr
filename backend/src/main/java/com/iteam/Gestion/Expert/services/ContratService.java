package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.entities.Contrat;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ContratService {

    void deleteContrat(Long id);
    List<Contratdto> listeallContrat ();
    Contratdto findByIdContrat(Long id);


    Contratdto addContrat(Contratdto contratdto);

    Contratdto uploadcoursfile(Long id, MultipartFile image);
}
