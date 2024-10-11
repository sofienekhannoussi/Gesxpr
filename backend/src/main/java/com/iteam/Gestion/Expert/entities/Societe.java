package com.iteam.Gestion.Expert.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Societe {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String nameSociete;
    private String description;
    @ManyToOne
   private ResponsableSociete responsable;



}
