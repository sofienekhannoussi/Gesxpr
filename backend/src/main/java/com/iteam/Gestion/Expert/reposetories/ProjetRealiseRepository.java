package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Diplomes;
import com.iteam.Gestion.Expert.entities.ProjetRealise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetRealiseRepository extends JpaRepository<ProjetRealise, Long> {

    @Query( "select o from ProjetRealise o where o.expert.id = :id" )
    List<ProjetRealise> findByIdprojet(@Param("id") Long id);
}

