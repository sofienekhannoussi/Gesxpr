package com.iteam.Gestion.Expert.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Competences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String competenceName;
    private Boolean isActive;
   // private String CreatedBy;
   // private Date CreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Expert expert;

}
