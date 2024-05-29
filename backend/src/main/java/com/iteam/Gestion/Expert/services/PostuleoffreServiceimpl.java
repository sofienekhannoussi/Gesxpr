package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Postuleoffre;

import java.util.List;
import java.util.Optional;

public class PostuleoffreServiceimpl implements PostuleoffreService{
    @Override
    public void deleteOffre(Long id) {

    }

    @Override
    public List<Postuleoffredto> listeallOffre() {
        return null;
    }

    @Override
    public Optional<Postuleoffre> findByIdOffre(Long id) {
        return Optional.empty();
    }

    @Override
    public Postuleoffre addOffre(Postuleoffredto postuleoffredto) {
        return null;
    }
}
