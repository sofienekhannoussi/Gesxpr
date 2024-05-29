package com.iteam.Gestion.Expert.reposetories;

import java.util.Optional;
import com.iteam.Gestion.Expert.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}