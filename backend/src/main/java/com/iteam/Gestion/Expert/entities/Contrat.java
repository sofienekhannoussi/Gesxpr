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
public class Contrat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long  id;
    private String reference;
    private String type;
    private Date duree;
    private String pdfContrat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Mission missions;
}
