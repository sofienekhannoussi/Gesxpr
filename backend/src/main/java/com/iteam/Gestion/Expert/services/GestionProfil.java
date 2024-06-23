package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;

public interface GestionProfil {


    Expertdto findExpertbyid(Long id );
    Expertdto updateExpert(Expertdto experDTO);

    ResponsableSocietedto findRespbyid(Long id );
    ResponsableSocietedto updateResp(ResponsableSocietedto profilResponsableDTO);


}
