package com.iteam.Gestion.Expert.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iteam.Gestion.Expert.dto.Admindto;
import com.iteam.Gestion.Expert.dto.Alluserliste;
import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.services.GestionCompte;
import com.iteam.Gestion.Expert.services.MissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/gestionCOmpte")
@RequiredArgsConstructor
public class GestionCompteContoller {
	private final GestionCompte gestionCompte;
@GetMapping("/findbyidexpert/{id}")
	public Expertdto findbyidExpert(@PathVariable("id") Long id) {
		return gestionCompte.findbyidExpert(id);
	}
@GetMapping("/findbyidAdmin/{id}")
	public Admindto findbyidAdmin(@PathVariable("id") Long id) {
		return gestionCompte.findbyidAdmin(id);
	}
@GetMapping("/findbyidResponsableSociete/{id}")
	public ResponsableSocietedto findbyidResponsableSociete(@PathVariable("id") Long id) {
		return gestionCompte.findbyidResponsableSociete(id);
	}
@PostMapping("/updaterespsoci")
	public ResponsableSocietedto updateResponsableSocietedto(ResponsableSocietedto eleedto) {
		return gestionCompte.updateResponsableSocietedto(eleedto);
	}
@PostMapping("/updateAdmin")
	public Admindto updateAdmindto(Admindto eleedto) {
		return gestionCompte.updateAdmindto(eleedto);
	}
@PostMapping("/updateExpert")
	public Expertdto updateExpertdto(Expertdto expdto) {
		return gestionCompte.updateExpertdto(expdto);
	}
@DeleteMapping("/deletebyid/{id}")
	public void deletebyid(@PathVariable("id")  Long id) {
		gestionCompte.deletebyid(id);
	}
@GetMapping("/alluser")
	public List<Alluserliste> listeall() {
		return gestionCompte.listeall();
	}



}
