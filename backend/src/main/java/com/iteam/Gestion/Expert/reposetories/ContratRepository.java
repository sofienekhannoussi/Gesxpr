package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Contrat;
import com.iteam.Gestion.Expert.entities.ProjetRealise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
