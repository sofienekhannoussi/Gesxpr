package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.configimage.ImageStorage;
import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Listpostuleoffre;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import com.iteam.Gestion.Expert.services.CompetencesService;
import com.iteam.Gestion.Expert.services.PostuleoffreService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("api/v1/offre")
@RequiredArgsConstructor
public class PostuloffreController {

    private final PostuleoffreService postuleoffreService;


    private final ImageStorage imageStorage;

    @DeleteMapping("/delete/{id}")

    public void deletePostule(@PathVariable("id")Long id) {
        postuleoffreService.deletePostule(id);
    }

    @GetMapping("/listeall/{idmission}")

    public List<ListPostuledto> listeallPostule(@PathVariable("idmission") Long idmission) {
        return postuleoffreService.listeallPostule(idmission);
    }
    @GetMapping("/countAll")
    public Long countallPostule() {
        return postuleoffreService.countallPostule();
    }

    @GetMapping("/listePostuleByMission/{idmission}")

    public List<Listpostuleoffre> listePostuleByIdMissions(@PathVariable("idmission") Long idmission) {
        return postuleoffreService.listePostuleByIdMission(idmission);
    }


    @GetMapping("/findByIdPostule/{id}")

    public ListPostuledto findByIdpostule(@PathVariable("id") Long id) {
        return postuleoffreService.findByIdpostule(id);
    }


    /*@PostMapping("/add")

     public Postuleoffredto addPostule(@RequestBody  Postuleoffredto postuleoffredto) {
         return postuleoffreService.addPostule(postuleoffredto, null);
     }*/
    @PostMapping (value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Postuleoffredto addPostule(
            @RequestParam("idexpert") Long idexpert,
            @RequestParam("idmission") Long idmission,
            @RequestParam("cvFile") MultipartFile cvFile) {
        Postuleoffredto postuleoffredto = new Postuleoffredto();
        postuleoffredto.setIdexpert(idexpert);
        postuleoffredto.setIdmission(idmission);

        return postuleoffreService.addPostule(postuleoffredto, cvFile);
    }

    @PutMapping (value = "/uploadFile/{Id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Expert uploadFiles(@PathVariable("Id") Long Id, @RequestParam MultipartFile image) {
        return postuleoffreService.uploadFile(Id, image);
    }

    @GetMapping("/listePostuleByExpert/{idmission}/{idexpert}")
    public Listpostuleoffre listePostuleByIdExpert(@PathVariable("idmission")  Long idmission , @PathVariable("idexpert")  Long idexpert ) {
        return postuleoffreService.listePostuleByIdExpert(idmission,idexpert);
    }
}
