package com.eath.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resultat_analyse")
public class ResultatAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    private Integer idResultat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateurs utilisateur;

    @Column(name = "resultat_allergene", columnDefinition = "TEXT")
    private String resultatAllergene;

    @Column(name = "conformite_halal")
    private Boolean conformiteHalal;

    @Column(name = "substance_nocive", columnDefinition = "TEXT")
    private String substanceNocive;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
