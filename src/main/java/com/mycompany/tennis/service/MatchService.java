package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Match;
import com.mycompany.tennis.Repository.MatchRepositoryImpl;
import com.mycompany.tennis.Repository.ScoreRepositoryImpl;

public class MatchService {
    private MatchRepositoryImpl matchRepository ;
    private ScoreRepositoryImpl scoreRepository ;

    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl() ;
    }


    public void enregistrerNouveauMatch(Match match){
        this.matchRepository.create(match);
        this.scoreRepository.create(match.getScore());

    }
}
