package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.ListPostuledto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.dto.Postuleoffredto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import com.iteam.Gestion.Expert.reposetories.PostuleoffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostuleoffreServiceimpl implements PostuleoffreService{
     private final  PostuleoffreRepository postuleoffreRepository;
    private final MissionRepesitory missionRepesitory;

     private final ExpertRepository expertRepository;


    @Override
    public void deletePostule(Long id) {
    postuleoffreRepository.deleteById(id);
    }

    @Override
    public List<ListPostuledto> listeallPostule() {
        return postuleoffreRepository.findAll().stream().map(
               ListPostuledto::fromEntity).collect(Collectors.toList());
    }



    @Override
    public ListPostuledto findByIdpostule(Long id) {


        Optional<Postuleoffre> postuleoffre = postuleoffreRepository.findById(id);
        if (postuleoffre.isPresent()) {
           ListPostuledto listPostuledto = ListPostuledto.fromEntity(postuleoffre.get());
            return listPostuledto;
        } else

            throw new RuntimeException("err");

    }


    @Override
    public Postuleoffredto addPostule(Postuleoffredto postuleoffredto) {
        Optional<Expert> expert = expertRepository.findById(postuleoffredto.getIdexpert());
        Optional<Mission> mission = missionRepesitory.findById(postuleoffredto.getIdmission());

        Postuleoffre postuleoffre = Postuleoffredto.toEntity(postuleoffredto);
        System.err.println(postuleoffre);
        if (expert.isPresent()&& mission.isPresent()) {
            postuleoffre.setExpert(expert.get());
          //  postuleoffre.setMissions(mission.get());

            postuleoffre.setDatepostule(new Date());
            Postuleoffre postulesaved = postuleoffreRepository.save(postuleoffre);
            mission.get().setPostuleoffre(postulesaved);
            missionRepesitory.save(mission.get());
            return Postuleoffredto.fromEntity(postulesaved);
        } else
            throw new RuntimeException("err");
    }
}
