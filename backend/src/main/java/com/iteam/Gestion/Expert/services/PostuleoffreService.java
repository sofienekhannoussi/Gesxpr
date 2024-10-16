package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Listpostuleoffre;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostuleoffreService {

    void deletePostule(Long id);
    List<ListPostuledto> listeallPostule (Long id);


    List<Listpostuleoffre> listePostuleByIdMission(Long id);
    Listpostuleoffre listePostuleByIdExpert(Long idmission, Long idexpert);



    ListPostuledto findByIdpostule(Long id);
    // changement de dto pour la liste et le detail


    Long countallPostule();

    Expert uploadFile(Long Id, MultipartFile image) ;


    // Postuleoffredto addPostule(Postuleoffredto postuleoffredto);
    Postuleoffredto addPostule(Postuleoffredto postuleoffredto, MultipartFile cvFile);

    Postuleoffre existingPostule(Expert expert, Mission mission);
}
