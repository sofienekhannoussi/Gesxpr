package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;

public interface GestionProfil {


    ProfilExpertdto findExpertbyid(Long id );
    ProfilExpertdto updateExpert(ProfilExpertdto profilExperDTO);

    ResponsableSocietedto findRespbyid(Long id );
    ResponsableSocietedto updateResp(ResponsableSocietedto profilResponsableDTO);


}
