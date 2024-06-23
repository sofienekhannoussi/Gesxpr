package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Diplomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiplomesRepository extends JpaRepository<Diplomes, Long> {

    @Query( "select o from Diplomes o where o.expert.id = :id" )
    List<Diplomes> findByIddiplomes(@Param("id") Long id);
}
