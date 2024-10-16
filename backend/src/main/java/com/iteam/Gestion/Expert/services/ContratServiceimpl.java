package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.configimage.ImageStorage;
import com.iteam.Gestion.Expert.dto.Contratdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Contrat;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.ContratRepository;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratServiceimpl implements ContratService{

    private final ImageStorage imageStorage;
    private final ContratRepository contratRepository;
    private final MissionRepesitory missionRepesitory;
    private final ExpertRepository expertRepository;
    private final ResponsableSocieteRepository responsableSocieteRepository;

    @Override
    public void deleteContrat(Long id) {

    }

    @Override
    public List<Contratdto> listeallContrat() {
        return contratRepository.findAll().stream().map(Contratdto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Contratdto findByIdContrat(Long id) {
        Optional<Contrat> optionalContrat = contratRepository.findById(id);
        optionalContrat.orElseThrow(() -> new RuntimeException("Cours not found with id: " + id));
        return Contratdto.fromEntity(optionalContrat.get());
    }

//    @Override
//    public Contratdto addContrat(Contratdto contratdto) {
//
//        return null;
//    }

    @Override
    public Contratdto addContrat(Contratdto contratdto) {
        Optional<ResponsableSociete> responsableSociete = responsableSocieteRepository.findById(contratdto.getResponsibleId());
        Optional<Mission> mission = missionRepesitory.findById(contratdto.getMissionId());
        Optional<Expert> expert = expertRepository.findById(contratdto.getExpertId());

        System.err.println(expert.get().getId());
        System.err.println(mission.get().getId());
        System.err.println(responsableSociete.get().getId());



        Contrat contrat = Contratdto.toEntity(contratdto);

        if(mission.isPresent() && expert.isPresent() && responsableSociete.isPresent() ) {
            contrat.setResponsableSociete(responsableSociete.get());
            contrat.setMissions(mission.get());
            contrat.setExpert(expert.get());
            return Contratdto.fromEntity(contratRepository.save(contrat));
        }
        else {
            throw new RuntimeException("Contrat not found mission or expert ");
        }
    }
















    public ResponseEntity<Contrat> findbyId(Long id) {
        if (id == null) {
            //  log.error("student ID is null");
            return null;
        }
        return ResponseEntity.ok(contratRepository.findById(id).get());

    }

    @Override
    public Long listeallContratbyExpert(Long id) {
        return contratRepository.countAllContraByExpert(id);
    }


    @Override
    public Long listeallContratbyResp(Long id) {
        return contratRepository.countAllContraByResp(id);
    }
    @Override
    public Contratdto uploadcoursfile(Long id, MultipartFile image) {
        ResponseEntity<Contrat> contratResponse = this.findbyId(id);
        String imageName=imageStorage.store(image);
        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/contrat/downloadcontratFile/").path(imageName).toUriString();
        Contrat contrat = contratResponse.getBody();

        if (contrat!=null)
            contrat.setPdfContrat(fileImageDownloadUrl);
        Contrat contratsaved = contratRepository.save(contrat);
        return  Contratdto.fromEntity(contratsaved);

    }

    @Override
    public Long countallContrat() {
        return contratRepository.countAllContra();
    }
}
