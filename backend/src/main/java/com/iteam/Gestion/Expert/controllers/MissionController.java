package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @DeleteMapping("/delete/{id}")
    public void deleteMision(@PathVariable("id") Long id) {
        missionService.deleteMission(id);
    }


    @GetMapping("/listeall")


    public List<Missiondto> listeallMission() {
        return missionService.listeallMission();
    }

    @GetMapping("/listeallbyresp/{id}")


    public List<Missiondto> listeallMissionByRESP(@PathVariable("id") Long id) {
        return missionService.listeallMissionbyResp(id);
    }

    @GetMapping("/findByIdMission/{id}")
    public Missiondto findByIdMission(@PathVariable("id") Long id) {return missionService.findByIdMission(id);
    }
    @PostMapping("/add")

    public Missiondto addMission(@RequestBody Missiondto missiondto) {
        return missionService.addMission(missiondto);
    }
    @PostMapping("/update")

    public Missiondto updateMission(@RequestBody Missiondto missiondto) {
        return missionService.updateMission(missiondto);
    }

}
