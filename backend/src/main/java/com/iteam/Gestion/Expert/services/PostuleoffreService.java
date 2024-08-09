package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostuleoffreService {

    void deletePostule(Long id);
    List<ListPostuledto> listeallPostule (Long id);


    ListPostuledto findByIdpostule(Long id);
    // changement de dto pour la liste et le detail



        Expert uploadFile(Long Id, MultipartFile image) ;


    Postuleoffredto addPostule(Postuleoffredto postuleoffredto);
}
