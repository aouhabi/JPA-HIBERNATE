package com.mycompany.tennis.service;

import com.mycompany.tennis.DTO.EpreuveDTO;
import com.mycompany.tennis.DTO.MatchDTO;
import com.mycompany.tennis.Entity.Epreuve;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Match;
import com.mycompany.tennis.Entity.Score;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.Repository.JoueurRepositoryImpl;
import com.mycompany.tennis.Repository.MatchRepositoryImpl;
import com.mycompany.tennis.Repository.ScoreRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private MatchRepositoryImpl matchRepository ;
    private ScoreRepositoryImpl scoreRepository ;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService() {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl() ;
        this.epreuveRepository= new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();
    }


   public Match getMatchByID(Long id){
        MatchDTO matchDTO = new MatchDTO();
        Session session = null;
        Transaction tx = null;
       try{
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           tx= session.beginTransaction();
           Match match = this.matchRepository.getById(id);
           Hibernate.initialize(match.getVainqeur());
           Hibernate.initialize(match.getFinalise());
           Hibernate.initialize(match.getEpreuve().getTournoi());
           Hibernate.initialize(match.getEpreuve());
           Hibernate.initialize(match.getScore());
           matchDTO.setFinalise(match.getFinalise());
           matchDTO.setVainqeur(match.getVainqeur());
           matchDTO.setId(match.getId());
           matchDTO.setEpreuve(match.getEpreuve());
           matchDTO.setScore(match.getScore());
           tx.commit();
       }catch (Throwable e){
           e.printStackTrace();
           tx.rollback();
       }finally {
           session.close();
       }

        return matchDTO ;
   }

   public void tapisVert(Long id){
       Session session = null;
       Transaction tx = null;
       try{
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           tx= session.beginTransaction();
           Match match = this.matchRepository.getById(id);
           Hibernate.initialize(match.getVainqeur());
           Hibernate.initialize(match.getFinalise());
          // Hibernate.initialize(match.getEpreuve().getTournoi());
           Hibernate.initialize(match.getEpreuve());
           Hibernate.initialize(match.getScore());
           match.getScore().setSet1((byte) 0);
           match.getScore().setSet2((byte) 0);
           match.getScore().setSet3((byte) 0);
           match.getScore().setSet4((byte) 0);
           match.getScore().setSet5((byte) 0);
           Joueur vainqeur = match.getVainqeur();
           match.setVainqeur(match.getFinalise());
           match.setFinalise(vainqeur);
           tx.commit();
       }catch (Throwable e){
           e.printStackTrace();
           tx.rollback();
       }finally {
           session.close();
       }

   }

   public void createMatch(Match match){
       Session session = null;
       Transaction tx = null;
       try{
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           tx= session.beginTransaction();
           match.setEpreuve(this.epreuveRepository.getById(match.getEpreuve().getId()));
           match.setFinalise(this.joueurRepository.getJoueurById(match.getFinalise().getId()));
           match.setVainqeur(this.joueurRepository.getJoueurById(match.getVainqeur().getId()));
           Score score = match.getScore() ;
           score.setMatch(match);
           match.setScore(score);
          // this.scoreRepository.create(score);
           this.matchRepository.create(match);
           tx.commit();
       }catch (Throwable e){
           e.printStackTrace();
           tx.rollback();
       }finally {
           session.close();
       }

   }

   public void supprimerMatch(Long id){
       Session session = null;
       Transaction tx = null;
       try{
           session = HibernateUtil.getSessionFactory().getCurrentSession();
           tx= session.beginTransaction();
           this.matchRepository.delete(id);
           tx.commit();
       }catch (Throwable e){
           e.printStackTrace();
           tx.rollback();
       }finally {
           session.close();
       }

   }
}
