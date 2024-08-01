package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Diplomes;
import com.iteam.Gestion.Expert.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepesitory extends JpaRepository<Mission, Long> {
    @Query( "select o from Mission o where o.responsableSociete.id = :id" )
    List<Mission> findByIdRespMission(@Param("id") Long id);

    @Query( "select o from Mission o where o.statut = :sta" )
    List<Mission> findByStatut(@Param("sta") String sta);
}
