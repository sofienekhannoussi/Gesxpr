package com.iteam.Gestion.Expert.services;



import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.entities.User;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;
import com.iteam.Gestion.Expert.reposetories.UserRepository;
import com.iteam.Gestion.Expert.configimage.ImageStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Service
@RequiredArgsConstructor



public class GestionProfilExpertImpl implements  GestionProfil {
    private final ExpertRepository expertRepository;
    private final UserRepository repository;
    private final ImageStorage imageStorage;


    private final ResponsableSocieteRepository responsableSocieteRepository;


    @Override
    public Expertdto findExpertbyid(Long id) {
        Optional<Expert> expert = expertRepository.findById(id);

        if (expert.isPresent()) {

            Expertdto expertdto = Expertdto.fromEntity(expert.get());
            return expertdto;
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public Expertdto updateExpert(Expertdto experDTO) {


        Optional<Expert> expert =expertRepository.findById(experDTO.getId());
        System.err.println(expert);
        System.err.println(experDTO.getId());
        if(expert.isPresent())
        {
            Expert exp =Expertdto.toEntity(experDTO);
            expert.get().setAdresse(exp.getAdresse());
            expert.get().setFullname(exp.getFullname());
            expert.get().setBiography(exp.getBiography());
            expert.get().setEmail(exp.getEmail());
            expert.get().setPhone(exp.getPhone());
            Expert updatedexp =expertRepository.save( expert.get());
            return Expertdto.fromEntity(updatedexp);
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



    public ResponseEntity<User> findbyId(Long id) {
        if (id == null) {
            //  log.error("student ID is null");
            return null;
        }
        return ResponseEntity.ok(repository.findById(id).get());

    }
    @Override


    public User uploadImage(Long Id, MultipartFile image) {
        ResponseEntity<User> userResponse = this.findbyId(Id);
        String imageName=imageStorage.store(image);
        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/profilexpert/downloadavatar/").path(imageName).toUriString();
        User user = userResponse.getBody();

        if (user!=null)
            user.setAvatar(fileImageDownloadUrl);
        User usersaved = repository.save(user);
        return usersaved ;
    }

    public ResponseEntity<Expert> findbyIdExpert(Long id) {
        if (id == null) {
            //  log.error("student ID is null");
            return null;
        }
        return ResponseEntity.ok(expertRepository.findById(id).get());

    }
    @Override

    public Expert uploadFile(Long Id, MultipartFile image) {
        ResponseEntity<Expert> userResponse = this.findbyIdExpert(Id);
        String imageName=imageStorage.store(image);
        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/auth/downloadFileCV/").path(imageName).toUriString();
        Expert expert = userResponse.getBody();

        if (expert!=null)
            expert.setCv(fileImageDownloadUrl);
        Expert expertsaved = expertRepository.save(expert);
        return expertsaved;
    }
}
