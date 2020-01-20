package com.mycompany.tennis;

import com.mycompany.tennis.Entity.*;
import com.mycompany.tennis.service.JoueurService;
import com.mycompany.tennis.service.MatchService;
import com.mycompany.tennis.service.TournoiService;

public class TestDeConnection {
    public static void main(String... args){
        TournoiService tournoiService = new TournoiService() ;
        JoueurService joueurService = new JoueurService() ;
        MatchService matchService = new MatchService();

        Tournoi tournoi = new Tournoi() ;
        tournoi.setNom("ATP Cup");
        tournoi.setCode("AC");

        Joueur joueur = new Joueur() ;
        joueur.setSexe('H');
        joueur.setPrenom("Adel");
        joueur.setNom("ohb");

        Score score = new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        Match match = new Match();
        match.setScore(score);
        score.setMatch(match);
        Joueur fedrer = joueurService.getJoueurById(32L);
        Joueur murray = joueurService.getJoueurById(34L);
        match.setVainqeur(fedrer);
        match.setFinalise(murray);
        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);
  //      matchService.enregistrerNouveauMatch(match);
//        joueurService.createJoueur(joueur);
//        tournoiService.createTournoi(tournoi);
//        Joueur j = joueurService.getJoueurById(50L);
//        System.out.println(j);
//            j.setSexe('F');
//            joueurService.updateJoueur(j);
//            joueurService.deleteJoueur(j.getId());
//            System.out.println( joueurService.getAllJoueur().size());

    }
}
