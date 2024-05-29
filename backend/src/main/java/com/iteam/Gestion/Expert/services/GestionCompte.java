package com.iteam.Gestion.Expert.services;

import java.util.List;

import com.iteam.Gestion.Expert.dto.Admindto;
import com.iteam.Gestion.Expert.dto.Alluserliste;
import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;

public interface GestionCompte {
	Expertdto findbyidExpert(Long id);
	Admindto findbyidAdmin(Long id);
	ResponsableSocietedto findbyidResponsableSociete(Long id );
	ResponsableSocietedto updateResponsableSocietedto(ResponsableSocietedto eleedto);
	Admindto updateAdmindto(Admindto eleedto);
	Expertdto updateExpertdto(Expertdto expdto);
	void deletebyid(Long id);
	List<Alluserliste> listeall();
	
	

}
