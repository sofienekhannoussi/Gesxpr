package com.iteam.Gestion.Expert.controllers;


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

    public ProfilExpertdto findExpertbyid(Long id) {
        return gestionProfilService.findExpertbyid(id);
    }
    @PostMapping("/savexpert")

    public ProfilExpertdto updateExpert(ProfilExpertdto profilExperDTO) {
        return gestionProfilService.updateExpert(profilExperDTO);
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
