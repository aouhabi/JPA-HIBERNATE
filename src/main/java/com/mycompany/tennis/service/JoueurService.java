package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.JoueurRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private JoueurRepositoryImpl repository ;

    public JoueurService() {
        this.repository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur){
        Session session = null;
        Transaction tx = null ;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            this.repository.create(joueur);
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

    public void renommeJoueur(Long id , String nom){
        Session session = null;
        Transaction tx = null ;
        Joueur joueur = getJoueurById(id);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            joueur.setNom(nom);
            Joueur joueurPersi = (Joueur) session.merge(joueur);
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
    public List<Joueur> getAllJoueur(){

        Session session = null;
        Transaction tx = null ;
        List<Joueur> joueurList = new ArrayList<>();
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            EntityManager entityManager = (EntityManager) Persistence.createEntityManagerFactory("tennis-unit");
            tx = session.beginTransaction() ;
            joueurList = this.repository.getAll() ;
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
        return joueurList;

    }
    public  Joueur getJoueurById(Long id){

        Session session = null;
        Transaction tx = null ;
        Joueur joueur= new Joueur();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            joueur = this.repository.getJoueurById(id);
            Hibernate.initialize(joueur.getEpreuves());
            session.flush();
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

        return  joueur;
    }
    public void deleteJoueur(Long id){
        Session session = null;
        Transaction tx = null ;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction() ;
            this.repository.deleteJoueur(id);
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
