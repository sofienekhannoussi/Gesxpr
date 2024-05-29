package com.iteam.Gestion.Expert.reposetories;

import com.iteam.Gestion.Expert.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin, Long> {
}
