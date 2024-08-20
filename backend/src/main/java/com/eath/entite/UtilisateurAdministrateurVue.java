package com.eath.entite;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "vue_utilisateur_administrateur") // Nom de la vue
public class UtilisateurAdministrateurVue implements UserDetails {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    private String motDePasse;

    @Column(name = "dateCreation")
    private Timestamp dateCreation;

    @Column(name = "photoDeProfil")
    private String photoDeProfil;

    @Column(name = "taille")
    private BigDecimal taille;

    @Column(name = "poids")
    private BigDecimal poids;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "conditionsMedicales")
    private String conditionsMedicales;

    @Column(name = "preference")
    private String preference;

    @Column(name = "niveauAbonnement")
    private String niveauAbonnement;

    @Column(name = "utilisateurDateModification")
    private Timestamp utilisateurDateModification;

    @Column(name = "niveauAdmin")
    private String niveauAdmin;

    @Column(name = "privileges")
    private String privileges;

    @Column(name = "administrateurDateModification")
    private Timestamp administrateurDateModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
