package com.eath.entite;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "administrateur")
public class Administrateur extends Personne {

    @Column(name = "niveau_admin")
    private String niveauAdmin;

    @Column(name = "privileges")
    private String privileges;

    @Column(name = "date_modification", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp dateModification;

    // Getters and Setters
    public String getNiveauAdmin() {
        return niveauAdmin;
    }

    public void setNiveauAdmin(String niveauAdmin) {
        this.niveauAdmin = niveauAdmin;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }
}
