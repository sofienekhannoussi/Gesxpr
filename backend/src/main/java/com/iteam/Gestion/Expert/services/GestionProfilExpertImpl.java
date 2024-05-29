package com.iteam.Gestion.Expert.services;



import com.iteam.Gestion.Expert.dto.ProfilExpertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor



public class GestionProfilExpertImpl implements  GestionProfil {
    private final ExpertRepository expertRepository;
    private final ResponsableSocieteRepository responsableSocieteRepository;


    @Override
    public ProfilExpertdto findExpertbyid(Long id) {
        Optional<Expert> expert = expertRepository.findById(id);

        if (expert.isPresent()) {

            ProfilExpertdto profilExpertdto = ProfilExpertdto.FromEntity(expert.get());
            return profilExpertdto;
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public ProfilExpertdto updateExpert(ProfilExpertdto profilExperDTO) {


        Optional<Expert> expert =expertRepository.findById(profilExperDTO.getId());
        System.err.println(expert);
        System.err.println(profilExperDTO.getId());
        if(expert.isPresent())
        {

            Expert statoupdate =expert.get();
            Expert exp =ProfilExpertdto.toEntity(profilExperDTO);
            statoupdate.setId(exp.getId());
            statoupdate.setFullname(exp.getFullname());
            statoupdate.setBiography(exp.getBiography());
            statoupdate.setEmail(exp.getEmail());
            statoupdate.setPhone(exp.getPhone());
            System.err.println("//////////////");
            System.err.println(statoupdate);
            Expert updatedexp =expertRepository.save(statoupdate);
            return ProfilExpertdto.FromEntity(updatedexp);
        }
        else
        {
            throw new RuntimeException("no Expert to update");
        }



    }











    @Override
    public ResponsableSocietedto findRespbyid(Long id) {

        ResponsableSociete repsocite =responsableSocieteRepository.findById(id).get();
        return ResponsableSocietedto.fromEntity(repsocite);
    }

    @Override
    public ResponsableSocietedto updateResp(ResponsableSocietedto profilResponsableDTO) {

        Optional<ResponsableSociete> responsableSociete = responsableSocieteRepository.findById(profilResponsableDTO.getId());
        System.err.println(responsableSociete);
        System.err.println(profilResponsableDTO.getId());
        if (responsableSociete.isPresent()) {
            ResponsableSociete statoupdate = responsableSociete.get();
            ResponsableSociete ste = ResponsableSocietedto.toEntity(profilResponsableDTO);
            statoupdate.setId(ste.getId());
            statoupdate.setFullname(ste.getFullname());
            statoupdate.setPresentationsociete(ste.getPresentationsociete());
            statoupdate.setEmail(ste.getEmail());
            statoupdate.setPhone(ste.getPhone());
            System.err.println("//////////////");
            System.err.println(statoupdate);
            ResponsableSociete updatedste = responsableSocieteRepository.save(statoupdate);
            return ResponsableSocietedto.fromEntity(updatedste);
        } else {
            throw new RuntimeException("no Manager to update");
        }


    }
}
