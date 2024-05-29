package com.iteam.Gestion.Expert.controllers;


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
   /* @GetMapping("/lister")
    public List<Mission> listeallMission() {
        return MissionService.listeallMission();
    }*/
    @GetMapping("/findByIdMission/{id}")
    public Optional<Mission> findByIdMission(@PathVariable("id") Long id) {return missionService.findByIdMission(id);
    }
    @PostMapping("/add")
    public Mission ad(@RequestBody Missiondto missiondto) {return missionService.addMission(missiondto);
    }
  /*  @PutMapping("/update/{id}")
    public Mission updateMission(Long id, Mission mission) {return missionService.updateMission(id, mission);
    }*/



}
