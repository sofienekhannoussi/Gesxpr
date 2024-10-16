package com.iteam.Gestion.Expert.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.iteam.Gestion.Expert.entities.User;
import org.springframework.stereotype.Service;

import com.iteam.Gestion.Expert.dto.Admindto;
import com.iteam.Gestion.Expert.dto.Alluserliste;
import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.Admin;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.AdminRepository;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;
import com.iteam.Gestion.Expert.reposetories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GestionCompteimpl implements GestionCompte {
    private final ExpertRepository expertRepository;
    private final AdminRepository adminRepository;
    private final ResponsableSocieteRepository responsableSocieteRepository;
    private final UserRepository userRepository;



    @Override
    public Alluserliste findbyidUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            User recupeleve=user.get();
            return Alluserliste.fromEntity(recupeleve);
        }else
            throw new RuntimeException("no user");
    }

    @Override
    public Expertdto findbyidExpert(Long id) {
        Optional<Expert> eleve =expertRepository.findById(id);
        if (eleve.isPresent())
        {
            Expert recupeleve=eleve.get();
            return Expertdto.fromEntity(recupeleve);
        }else

            throw new RuntimeException("no student");
    }

    @Override
    public Admindto findbyidAdmin(Long id) {
        Optional<Admin> eleve =adminRepository.findById(id);
        if (eleve.isPresent())
        {
            Admin recupeleve=eleve.get();
            return Admindto.fromEntity(recupeleve);
        }else

            throw new RuntimeException("no student");
    }

    @Override
    public ResponsableSocietedto findbyidResponsableSociete(Long id) {
        Optional<ResponsableSociete> eleve =responsableSocieteRepository.findById(id);
        if (eleve.isPresent())
        {
            ResponsableSociete recupeleve=eleve.get();
            return ResponsableSocietedto.fromEntity(recupeleve);
        }else

            throw new RuntimeException("no student");
    }

    @Override
    public Expertdto updateExpertdto(Expertdto expdto) {
        //Optional<Expert> expert =expertRepository.findById(expdto.getId());
        if(expdto.getId() != null)
        {
            Expert sta =Expertdto.toEntity(expdto);
            Expert updatedsta =expertRepository.save(sta);
            return Expertdto.fromEntity(updatedsta);
        }
        else
        {
            throw new RuntimeException("no stagire to update");
        }





    }

    @Override
    public Admindto updateAdmindto(Admindto eleedto) {
        if(eleedto.getId() != null)
        {
            Admin sta =Admindto.toEntity(eleedto);
            Admin updatedsta =adminRepository.save(sta);
            return Admindto.fromEntity(updatedsta);
        }
        else
        {
            throw new RuntimeException("no admin to update");
        }
    }

    @Override
    public ResponsableSocietedto updateResponsableSocietedto(ResponsableSocietedto eleedto) {
        if(eleedto.getId() != null)
        {
            ResponsableSociete sta =ResponsableSocietedto.toEntity(eleedto);
            ResponsableSociete updatedsta =responsableSocieteRepository.save(sta);
            return ResponsableSocietedto.fromEntity(updatedsta);
        }
        else
        {
            throw new RuntimeException("no admin to update");
        }
    }

    @Override
    public void deletebyid(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Alluserliste> listeall() {

        return userRepository.findAll()
                .stream()
                .map(Alluserliste::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public Alluserliste updateStatudto(Boolean status, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            user.get().setActive(status);

            return Alluserliste.fromEntity(userRepository.save(user.get())) ;
        }else
            throw new RuntimeException("no user");
    }
}






