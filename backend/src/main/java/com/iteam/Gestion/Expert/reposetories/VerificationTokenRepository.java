package com.iteam.Gestion.Expert.reposetories;

import java.util.Optional;

import com.iteam.Gestion.Expert.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;




public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
}
