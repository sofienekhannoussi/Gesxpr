package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Missiondto;
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
	public Optional<Mission> findByIdMission(Long id) {
		Optional<Mission> mission = missionRepesitory.findById(id);
		if (mission.isPresent()) {
			return mission;
		} else

			return null;
	}

	@Override
	public Mission addMission(Missiondto missiondto) {
		Mission mission = Missiondto.toEntity(missiondto);
		Optional<ResponsableSociete> optreponsable = responsableSocieteRepository
				.findById(missiondto.getIdresponsablesoci());
		if (optreponsable.isPresent()) {
			mission.setResponsableSociete(optreponsable.get());
			Mission missionsaved = missionRepesitory.save(mission);
			return missionsaved;
		} else
			throw new RuntimeException("err");

	}

}
