package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.auth.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;







public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String passwordResetToken);
}
