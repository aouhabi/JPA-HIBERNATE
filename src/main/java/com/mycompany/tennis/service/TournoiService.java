package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Tournoi;
import com.mycompany.tennis.Repository.TournoiRepositoryImpl;

import java.util.List;

public class TournoiService {
    private TournoiRepositoryImpl repository ;

    public TournoiService() {
        this.repository = new TournoiRepositoryImpl() ;
    }

    public void createTournoi(Tournoi tournoi){
        this.repository.create(tournoi);
    }

    public void updateTournois(Tournoi tournoi){
        this.repository.update(tournoi);
    }
    public List<Tournoi> getAllTournois(){
        return  this.repository.getTournoiList() ;
    }
    public  Tournoi getTournoiById(Long id){
        return  this.repository.getTournoiById(id);
    }
    public void deleteTournoi(Long id){
        this.repository.deleteTournois(id);
    }
}
