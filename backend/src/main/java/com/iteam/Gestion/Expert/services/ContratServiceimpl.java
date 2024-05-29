package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.entities.Contrat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratServiceimpl implements ContratService{
    @Override
    public void deleteContrat(Long id) {

    }

    @Override
    public List<Contratdto> listeallContrat() {
        return null;
    }

    @Override
    public Optional<Contrat> findByIdContrat(Long id) {
        return Optional.empty();
    }

    @Override
    public Contrat addContrat(Contratdto contratdto) {
        return null;
    }
}
