package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.dto.Diplomesdto;
import com.iteam.Gestion.Expert.dto.Missiondto;
import com.iteam.Gestion.Expert.entities.Diplomes;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.ResponsableSociete;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import com.iteam.Gestion.Expert.reposetories.PostuleoffreRepository;
import com.iteam.Gestion.Expert.reposetories.ResponsableSocieteRepository;
import java.util.TreeMap;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceimpl implements MissionService {

	private final PostuleoffreRepository postuleoffreRepository;
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
	public List<Missiondto> listeallMissionbystatut(String sta, Long id) {
		return missionRepesitory.findByStatut(sta , id).stream().map(Missiondto::fromEntity).collect(Collectors.toList());
	}
	@Override
	public List<Missiondto> listeallMissionbystatuts(String sta) {
		return missionRepesitory.findByStatuts(sta).stream().map(Missiondto::fromEntity).collect(Collectors.toList());
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
			statoupdate.setTitle(mission1.getTitle());
			statoupdate.setDescription(mission1.getDescription());
			statoupdate.setDateDebut(mission1.getDateDebut());
			statoupdate.setDateFin(mission1.getDateFin());
			statoupdate.setStatut(mission1.getStatut());
			statoupdate.setIsActive(mission1.getIsActive());
			statoupdate.setTypeContrat(mission1.getTypeContrat());
			statoupdate.setTypeLieu(mission1.getTypeLieu());


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



	public Long countPostuleoffreForMission(Long mission_id) {
		Optional<Mission> mission=missionRepesitory.findById(mission_id);
		return postuleoffreRepository.countByMissions(mission.get());
	}
	@Override
	public Long getMissionCountByResponsableSociete(Long responsableSocieteId) {
		return missionRepesitory.countMissionsByResponsableSociete(responsableSocieteId);
	}

	@Override
	public Map<Integer, Map<Integer, Long>> getMissionCountByMonthAndYear() {
		List<Object[]> results = missionRepesitory.countMissionByMonthAndYear();
		return results.stream()
				.collect(Collectors.groupingBy(
						result -> ((Number) result[0]).intValue(),
						TreeMap::new,
						Collectors.toMap(
								result -> ((Number) result[1]).intValue(),
								result -> ((Number) result[2]).longValue(),
								(oldValue, newValue) -> oldValue,
								TreeMap::new
						)
				));


	}
	@Override
	public Long countallMission() {
		return missionRepesitory.countAllMissions();
	}

}

