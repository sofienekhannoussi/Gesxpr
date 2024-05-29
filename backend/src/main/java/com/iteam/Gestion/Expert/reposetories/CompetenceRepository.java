package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Competences;
import com.iteam.Gestion.Expert.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository  <Competences, Long>{
}
