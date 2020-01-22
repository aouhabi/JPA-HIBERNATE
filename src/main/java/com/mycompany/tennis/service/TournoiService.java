package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Tournoi;
import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TournoiService {
    private TournoiRepositoryImpl repository ;

    public TournoiService() {
        this.repository = new TournoiRepositoryImpl() ;
    }

    public void createTournoi(Tournoi tournoi){
        this.repository.create(tournoi);
        EntityManager em = null;
       // EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            EntityManagerHolder.beginTransaction();
            this.repository.create(tournoi);
            EntityManagerHolder.commit();
        }catch (Throwable e){
            e.printStackTrace();
            EntityManagerHolder.rollback();
        }
        finally {
            if(em != null){
               em.close();
            }
        }

    }

    public void updateTournois(Tournoi tournoi){
        this.repository.update(tournoi);
    }
    public List<Tournoi> getAllTournois(){
        return  this.repository.getTournoiList() ;
    }
    public  Tournoi getTournoiById(Long id){
        EntityManager em = null;
        EntityTransaction tx = null ;
        Tournoi tournoi = null;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoi = this.repository.getTournoiById(id);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(em != null){
                em.close();
            }
        }

        return tournoi ;
    }
    public void deleteTournoi(Long id){
            EntityManager em = null ;
            EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getEntityManager() ;
            tx = em.getTransaction() ;
            tx.begin();
            this.repository.deleteTournois(id);
            tx.commit();
        }catch (Throwable e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            if(em != null){
                em.close();
            }
        }
    }
}
