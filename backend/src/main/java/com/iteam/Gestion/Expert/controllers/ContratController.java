package com.iteam.Gestion.Expert.controllers;

import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.entities.Contrat;
import com.iteam.Gestion.Expert.services.ContratService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/contrat")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService contratService;


    @DeleteMapping("/delete/{id}")
    public void deleteContrat(Long id) {
        contratService.deleteContrat(id);
    }




    public List<Contratdto> listeallContrat() {
        return contratService.listeallContrat();
    }


    @GetMapping("/findByIdContrat/{id}")
    public Optional<Contrat> findByIdContrat(Long id) {
        return contratService.findByIdContrat(id);
    }


    @PostMapping("/save")
    public Contrat addContrat(Contratdto contratdto) {
        return contratService.addContrat(contratdto);
    }

}
