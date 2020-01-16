package com.mycompany.tennis.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TOURNOI")
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id ;
   private String nom;
   private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournoi tournoi = (Tournoi) o;
        return Objects.equals(id, tournoi.id) &&
                Objects.equals(nom, tournoi.nom) &&
                Objects.equals(code, tournoi.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nom, code);
    }

    @Override
    public String toString() {
        return "Tournoi{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
