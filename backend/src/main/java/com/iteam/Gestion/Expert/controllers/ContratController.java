package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.configimage.ImageStorage;
import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.entities.Contrat;
import com.iteam.Gestion.Expert.services.ContratService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/contrat")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService contratService;
    private final ImageStorage imageStorage ;

    @GetMapping("/countAll")
    public Long countallcontrat() {
        return contratService.countallContrat();
    }



    @DeleteMapping("/delete/{id}")
    public void deleteContrat(@PathVariable("id") Long id) {
        contratService.deleteContrat(id);
    }

    @GetMapping("/findall")
    public List<Contratdto> listeallContratB() {
        return contratService.listeallContrat();
    }

    @GetMapping("/findallContratByResp/{id}")
    public List<Contratdto> listeallContratyRespID(@PathVariable("id") Long id) {
        return contratService.listeallContratByResp(id);
    }

    @GetMapping("/findallContratByExpert/{id}")
    public List<Contratdto> listeallContratyExpertID(@PathVariable("id") Long id) {
        return contratService.listeallContratByExpert(id);
    }
    @GetMapping("/NombreContratbyexpert/{id}")
    public Long listeallContratByExpert(@PathVariable("id") Long id) {
        return contratService.listeallContratbyExpert(id);
    }

    @GetMapping("/NombreContratbyresp/{id}")
    public Long listeallContratByResp(@PathVariable("id") Long id) {
        return contratService.listeallContratbyResp(id);
    }

    @GetMapping("/findByIdContrat/{id}")
    public Contratdto findByIdContrat(@PathVariable("id")  Long id) {
        return contratService.findByIdContrat(id);
    }

    @PostMapping("/save")
    public Contratdto addContrat(@RequestBody Contratdto contratdto ) {
        return contratService.addContrat(contratdto );
    }
    @PostMapping("/uploadFile/{id}")

    public Contratdto uploadcoursfile(@PathVariable("id") Long id, MultipartFile image) {
        return contratService.uploadcoursfile(id, image);
    }
    @GetMapping("/downloadcontratFile/{imageName}")
    public ResponseEntity<Resource> downloadTeacherImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }

}
