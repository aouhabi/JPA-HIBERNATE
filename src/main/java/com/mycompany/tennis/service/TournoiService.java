package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.Entity.Tournoi;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = null;
        Transaction tx = null ;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            this.repository.deleteTournois(id);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
}
