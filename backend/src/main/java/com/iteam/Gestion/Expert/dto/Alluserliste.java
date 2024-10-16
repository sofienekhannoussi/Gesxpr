package com.iteam.Gestion.Expert.dto;

import com.iteam.Gestion.Expert.auth.RegisterRequest;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Role;
import com.iteam.Gestion.Expert.entities.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Alluserliste {
	 private Long  id;
	    private String fullname;
	    private String email;
	    private String phone;
	    private boolean active;
	    private Role role;
	    public static Alluserliste fromEntity(User request)
	    {
	        return Alluserliste.builder()
	        		.id(request.getId())
	                .fullname(request.getFullname())
	                .email(request.getEmail())
	                .phone(request.getPhone())
	                .role(request.getRole())
	                .active(request.isActive())
	                .build();


	    }
}
