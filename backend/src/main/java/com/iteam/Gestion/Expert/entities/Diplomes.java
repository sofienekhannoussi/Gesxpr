package com.iteam.Gestion.Expert.entities;



import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diplomes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String title;

    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String universityName;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Expert expert;

}
