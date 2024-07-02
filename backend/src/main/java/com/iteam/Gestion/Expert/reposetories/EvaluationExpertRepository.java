package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.EvaluationExpert;
import com.iteam.Gestion.Expert.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationExpertRepository  extends JpaRepository<EvaluationExpert, Long> {
}
