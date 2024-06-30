package com.iteam.Gestion.Expert.controllers;


import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import com.iteam.Gestion.Expert.services.CompetencesService;
import com.iteam.Gestion.Expert.services.PostuleoffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/offre")
@RequiredArgsConstructor
public class PostuloffreController {

    private final PostuleoffreService postuleoffreService;

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
}
