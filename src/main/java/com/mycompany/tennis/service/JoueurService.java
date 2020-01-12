package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Repository.JoueurRepositoryImpl;

import java.util.List;

public class JoueurService {
    private JoueurRepositoryImpl repository ;

    public JoueurService() {
        this.repository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur){
        this.repository.create(joueur);
    }

    public void updateJoueur(Joueur joueur){
        this.repository.update(joueur);
    }
    public List<Joueur> getAllJoueur(){
        return  this.repository.getJoueurList() ;
    }
    public  Joueur getJoueurById(Long id){
        return  this.repository.getJoueurById(id);
    }
    public void deleteJoueur(Long id){
        this.repository.deleteJoueur(id);
    }
}
