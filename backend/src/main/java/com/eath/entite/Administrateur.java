package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "administrateur")
@DiscriminatorValue("ADMINISTRATEUR")
@Data
public class Administrateur extends Personne {

    @Column(name = "niveau_admin")
    private String niveauAdmin;

    @Column(name = "privileges")
    private String privileges;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;
}
