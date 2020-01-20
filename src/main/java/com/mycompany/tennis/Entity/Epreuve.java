package com.mycompany.tennis.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EPREUVE")
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private short annee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOURNOI")
    private Tournoi tournoi;
    @Column(name = "TYPE_EPREUVE")
    private Character typeEpreuve;

    @ManyToMany()
    @JoinTable(name = "PARTICIPANTS",
               joinColumns = {@JoinColumn(name = "ID_EPREUVE")},
               inverseJoinColumns = {@JoinColumn(name = "ID_JOUEUR")}
                )
    Set<Joueur> participants ;

    public Long getId() {
        return id;
    }

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getAnnee() {
        return annee;
    }

    public void setAnnee(short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epreuve epreuve = (Epreuve) o;
        return Objects.equals(id, epreuve.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "id=" + id +
                ", annee=" + annee +
                ", tournoi=" + tournoi.getNom() +
                ", typeEpreuve=" + typeEpreuve +
                '}';
    }
}
