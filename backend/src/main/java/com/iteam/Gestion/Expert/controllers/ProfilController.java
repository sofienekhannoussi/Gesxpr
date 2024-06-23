package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.services.GestionProfil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profilexpert")
@RequiredArgsConstructor
public class ProfilController {
    private final GestionProfil gestionProfilService;

    @GetMapping("/findExpertbyid/{id}")

    public Expertdto findExpertbyid(@PathVariable("id") Long id) {
        return gestionProfilService.findExpertbyid(id);
    }
    @PostMapping("/savexpert")

    public Expertdto updateExpert(@RequestBody Expertdto expertdto) {
        return gestionProfilService.updateExpert(expertdto);
    }
    @GetMapping("/findRespbyid/{id}")

    public ResponsableSocietedto findRespbyid(Long id) {
        return gestionProfilService.findRespbyid(id);
    }
    @PostMapping("/saveResponsable")

    public ResponsableSocietedto updateResp(ResponsableSocietedto profilResponsableDTO) {
        return gestionProfilService.updateResp(profilResponsableDTO);
    }
}
