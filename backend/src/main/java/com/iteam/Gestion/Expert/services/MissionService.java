package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionService {

    void deleteMission(Long id);
    List<Missiondto> listeallMission ();
    Optional<Mission> findByIdMission(Long id);
    Mission addMission(Missiondto missiondto);
   

}
