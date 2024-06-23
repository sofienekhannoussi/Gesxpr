package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Diplomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenceRepository extends JpaRepository  <Competences, Long>{
    @Query( "select o from Competences o where o.expert.id = :id" )
    List<Competences> findByIdcompetences(@Param("id") Long id);
}

