package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionService {

    void deleteMission(Long id);
    List<Missiondto> listeallMission ();
    List<Missiondto> listeallMissionbyResp (Long id);


    Missiondto findByIdMission(Long id);

    Missiondto updateMission(Missiondto missiondto);

    Missiondto addMission(Missiondto missiondto);
   

}
