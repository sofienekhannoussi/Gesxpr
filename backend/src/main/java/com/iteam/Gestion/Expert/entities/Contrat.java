package com.iteam.Gestion.Expert.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private String duree;
    private String pdfContrat;
    @JsonFormat(pattern="yyyy-MM-dd")

    private LocalDate dateContrat;
     //  validé
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Mission missions;
    @ManyToOne()
    private ResponsableSociete responsableSociete;
    @OneToOne()
    private Expert expert;
}
