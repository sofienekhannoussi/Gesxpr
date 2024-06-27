package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.User;
import com.iteam.Gestion.Expert.services.GestionProfil;
import com.iteam.Gestion.Expert.configimage.ImageStorage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/profilexpert")
@RequiredArgsConstructor
public class ProfilController {
    private final GestionProfil gestionProfilService;
    private final ImageStorage imageStorage;


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


    @PostMapping("/uploadImage/{Id}")
    public User uploadImage(@PathVariable("Id") Long Id, @RequestParam MultipartFile image) {
        return gestionProfilService.uploadImage(Id, image);
    }

    @GetMapping("/downloadavatar/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }

    @PostMapping("/uploadFile/{Id}")
    public Expert uploadFiles(@PathVariable("Id") Long Id, @RequestParam MultipartFile image) {
        return gestionProfilService.uploadFile(Id, image);
    }

    @GetMapping("/downloadFileCV/{imageName}")
    public ResponseEntity<Resource> downloadFilecv(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }
}
