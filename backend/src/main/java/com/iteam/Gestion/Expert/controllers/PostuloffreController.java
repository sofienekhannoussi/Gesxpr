package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.configimage.ImageStorage;
import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import com.iteam.Gestion.Expert.services.CompetencesService;
import com.iteam.Gestion.Expert.services.PostuleoffreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/findByIdPostule/{id}")

    public ListPostuledto findByIdpostule(@PathVariable("id") Long id) {
        return postuleoffreService.findByIdpostule(id);
    }


    @PostMapping("/add")

    public Postuleoffredto addPostule(@RequestBody  Postuleoffredto postuleoffredto) {
        return postuleoffreService.addPostule(postuleoffredto);
    }

    @PostMapping("/uploadFile/{Id}")
    public Expert uploadFiles(@PathVariable("Id") Long Id, @RequestParam MultipartFile image) {
        return postuleoffreService.uploadFile(Id, image);
    }

    @GetMapping("/downloadFileCV/{imageName}")
    public ResponseEntity<Resource> downloadFilecv(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }
}
