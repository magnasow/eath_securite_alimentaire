package com.eath.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types_produit")
public class TypesProduits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_produit")
    private Integer idTypeProduit;

    @Column(name = "nom_type_produit", length = 100, nullable = false)
    private String nomTypeProduit;

    @Column(name = "description_type_produit", columnDefinition = "TEXT")
    private String descriptionTypeProduit;

    @Column(name = "date_creation", updatable = false)
    private Timestamp dateCreation;

    @Column(name = "date_modification")
    private Timestamp dateModification;

    @PrePersist
    protected void onCreate() {
        dateCreation = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = Timestamp.from(Instant.now());
    }
}
