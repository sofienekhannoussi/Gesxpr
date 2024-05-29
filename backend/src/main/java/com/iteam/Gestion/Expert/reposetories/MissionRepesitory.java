package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepesitory extends JpaRepository<Mission, Long> {
}
