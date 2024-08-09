package com.eath.entite;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "historique_scans")
public class HistoriqueScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_scan")
    private Integer idHistoriqueScan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateurs utilisateurs;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produits produits;

    @Column(name = "date_scan", nullable = false, updatable = false)
    private LocalDateTime dateScan;

    @PrePersist
    protected void onCreate() {
        dateScan = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getIdHistoriqueScan() {
        return idHistoriqueScan;
    }

    public void setIdHistoriqueScan(Integer idHistoriqueScan) {
        this.idHistoriqueScan = idHistoriqueScan;
    }

    public Utilisateurs getUtilisateur() {
        return utilisateurs;
    }

    public void setUtilisateur(Utilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Produits getProduit() {
        return produits;
    }

    public void setProduit(Produits produits) {
        this.produits = produits;
    }

    public LocalDateTime getDateScan() {
        return dateScan;
    }

    public void setDateScan(LocalDateTime dateScan) {
        this.dateScan = dateScan;
    }
}
