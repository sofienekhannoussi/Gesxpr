package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface GestionProfil {


    Expertdto findExpertbyid(Long id );
    Expertdto updateExpert(Expertdto experDTO);

    ResponsableSocietedto findRespbyid(Long id );
    ResponsableSocietedto updateResp(ResponsableSocietedto profilResponsableDTO);

    User uploadImage(Long Id, MultipartFile image);

    public Expert uploadFile(Long Id, MultipartFile image);

    }
