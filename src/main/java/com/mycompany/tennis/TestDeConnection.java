package com.mycompany.tennis;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Tournoi;
import com.mycompany.tennis.Repository.TournoiRepositoryImpl;
import com.mycompany.tennis.service.JoueurService;
import com.mycompany.tennis.service.TournoiService;

public class TestDeConnection {
    public static void main(String... args){
        TournoiService tournoiService = new TournoiService() ;
        JoueurService joueurService = new JoueurService() ;
        Tournoi tournoi = new Tournoi() ;
        tournoi.setNom("ATP Cup");
        tournoi.setCode("AC");

        Joueur joueur = new Joueur() ;
        joueur.setSexe('H');
        joueur.setPrenom("Adel");
        joueur.setNom("ohb");

       // joueurService.createJoueur(joueur);
        Joueur j = joueurService.getJoueurById(50L);
        System.out.println(j);
            j.setSexe('F');
            joueurService.updateJoueur(j);
            joueurService.deleteJoueur(j.getId());
            System.out.println( joueurService.getAllJoueur().size());

    }
}
