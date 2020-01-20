package com.mycompany.tennis.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MATCH_TENNIS")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VAINQUEUR")
    private Joueur vainqeur ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FINALISTE")
    private Joueur finalise ;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EPREUVE")
    private Epreuve epreuve ;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "match",cascade = CascadeType.ALL)
    private Score score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Joueur getVainqeur() {
        return vainqeur;
    }

    public void setVainqeur(Joueur vainqeur) {
        this.vainqeur = vainqeur;
    }

    public Joueur getFinalise() {
        return finalise;
    }

    public void setFinalise(Joueur finalise) {
        this.finalise = finalise;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}
