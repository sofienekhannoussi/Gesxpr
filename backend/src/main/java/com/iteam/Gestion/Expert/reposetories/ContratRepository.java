package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Contrat;
import com.iteam.Gestion.Expert.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    @Query("SELECT COUNT(m) FROM Contrat m ")
    Long countAllContra();


    @Query( "select o from Contrat o where o.expert.id = :id" )
    List<Contrat> findByIdExpertContrat(@Param("id") Long id);
    @Query( "select o from Contrat o where o.responsableSociete.id = :id" )
    List<Contrat> findByIdRespContrat(@Param("id") Long id);
}
