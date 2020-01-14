package com.mycompany.tennis.Entity;

public class Match {

    private Long id;
    private Joueur vainqeur ;
    private Joueur finalise ;
    private Epreuve epreuve ;
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
}
