package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Mission;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MissionService {

    void deleteMission(Long id);
    List<Missiondto> listeallMission ();
    List<Missiondto> listeallMissionbyResp (Long id);

    List<Missiondto> listeallMissionbystatut(String sta , Long id);

    List<Missiondto> listeallMissionbystatuts(String sta);
    Missiondto findByIdMission(Long id);

    Missiondto updateMission(Missiondto missiondto);

    Missiondto addMission(Missiondto missiondto);

    Long countPostuleoffreForMission(Long mission_id);
    public Long getMissionCountByResponsableSociete(Long responsableSocieteId);
    Map<Integer, Map<Integer, Long>> getMissionCountByMonthAndYear();

    Long countallMission();
}
