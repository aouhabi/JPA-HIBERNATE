package com.mycompany.tennis.Entity;

public class Epreuve {
    private Long id ;
    private short annes;
    private Tournoi tournoi;
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getAnnes() {
        return annes;
    }

    public void setAnnes(short annes) {
        this.annes = annes;
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
}
