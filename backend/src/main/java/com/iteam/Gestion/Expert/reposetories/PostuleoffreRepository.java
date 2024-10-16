package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Diplomes;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostuleoffreRepository extends JpaRepository <Postuleoffre, Long> {
   // @Query( "select o from Postuleoffre o where o.missions.id = :id" )
    //List<Postuleoffre> findByIdPostule(@Param("id") Long id);
    Long countByMissions(Mission mission);
    Optional<Postuleoffre> findByExpertAndMissions(Expert expert, Mission mission);
    List<Postuleoffre> findByMissions_Id(Long missionId);

    Postuleoffre findByMissionsIdAndExpertId(Long idMission, Long idExpert);
    @Query("SELECT COUNT(m) FROM Postuleoffre m ")
    Long countAllPostules();
}
