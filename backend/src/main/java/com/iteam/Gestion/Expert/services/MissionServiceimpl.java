package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Competencesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceimpl implements MissionService {

	private final MissionRepesitory missionRepesitory;
	private final ResponsableSocieteRepository responsableSocieteRepository;

	@Override
	public void deleteMission(Long id) {
		missionRepesitory.deleteById(id);
	}

	@Override
	public List<Missiondto> listeallMission() {
		return missionRepesitory.findAll().stream().map(Missiondto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<Missiondto> listeallMissionbyResp(Long id) {
		return missionRepesitory.findByIdRespMission(id).stream().map(Missiondto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Missiondto findByIdMission(Long id) {
		Optional<Mission> mission = missionRepesitory.findById(id);
		if (mission.isPresent()) {
			Missiondto missiondto = Missiondto.fromEntity(mission.get());
			return missiondto;
		} else

			return null;

	}

	@Override
	public Missiondto updateMission(Missiondto missiondto) {


		Optional<Mission> mission =missionRepesitory.findById(missiondto.getId());
		if(mission.isPresent())
		{

			Mission statoupdate =mission.get();
			Mission mission1 = Missiondto.toEntity(missiondto);
			statoupdate.setReference(mission1.getReference());
			statoupdate.setTitle(mission1.getTitle());
			statoupdate.setDescription(mission1.getDescription());
			statoupdate.setDateDebut(mission1.getDateDebut());
			statoupdate.setDateFin(mission1.getDateFin());
			statoupdate.setStatut(mission1.getStatut());
			statoupdate.setIsActive(mission1.getIsActive());
			statoupdate.setTypeContrat(mission1.getTypeContrat());
			statoupdate.setTypeLieu(mission1.getTypeLieu());
			statoupdate.setTypeTravail(mission1.getTypeTravail());


			Mission updatemission = missionRepesitory.save(statoupdate);
			return Missiondto.fromEntity(updatemission);
		}
		else
		{
			throw new RuntimeException("no Mission to update");
		}

	}


	@Override
	public Missiondto addMission(Missiondto missiondto) {


		Optional<ResponsableSociete> responsableSociete = responsableSocieteRepository.findById(missiondto.getIdresponsablesoci());

	Mission mission = Missiondto.toEntity(missiondto);
		if (responsableSociete.isPresent()) {
			mission.setResponsableSociete(responsableSociete.get());
			Mission missionsaved = missionRepesitory.save(mission);
			return Missiondto.fromEntity(missionsaved);
		} else
			throw new RuntimeException("err");
	}
}

