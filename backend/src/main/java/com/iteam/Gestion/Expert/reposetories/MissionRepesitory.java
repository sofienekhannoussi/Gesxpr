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

    @Query( "select o from Mission o where o.statut = :sta and o.responsableSociete.id = :id" )
    List<Mission> findByStatut(@Param("sta") String sta, @Param("id") Long id );

    @Query( "select o from Mission o where o.statut = :sta"  )
    List<Mission> findByStatuts(@Param("sta") String sta );
//sesion responsable
    @Query("SELECT COUNT(m) FROM Mission m WHERE m.responsableSociete.id = :responsableSocieteId")
    Long countMissionsByResponsableSociete(@Param("responsableSocieteId") Long responsableSocieteId);

    @Query("SELECT EXTRACT(YEAR FROM r.dateDebut) AS year, EXTRACT(MONTH FROM r.dateDebut) AS month, COUNT(r) AS count " +
            "FROM Mission r " +
            "GROUP BY EXTRACT(YEAR FROM r.dateDebut), EXTRACT(MONTH FROM r.dateDebut) " +
            "ORDER BY year, month")
    List<Object[]> countMissionByMonthAndYear();

    @Query("SELECT COUNT(m) FROM Mission m ")
    Long countAllMissions();
}
