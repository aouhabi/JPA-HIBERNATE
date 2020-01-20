package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Score;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.ScoreRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    ScoreRepositoryImpl scoreRepository ;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public  void createScore(Score score){
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx= session.beginTransaction();
            this.scoreRepository.create(score);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
    }

    public  Score getScoreById(Long id){
        Score score = new Score();
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx= session.beginTransaction();
            score = this.scoreRepository.getById(id);
            Hibernate.initialize(score.getMatch());
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
        }
        return  score;
    }
}
