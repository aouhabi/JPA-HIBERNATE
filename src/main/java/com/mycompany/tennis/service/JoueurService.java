package com.mycompany.tennis.service;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.Repository.JoueurRepositoryImpl;
import org.hibernate.Hibernate;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private JoueurRepositoryImpl repository ;

    public JoueurService() {
        this.repository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur){
        EntityManager em = null;
        EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction() ;
            tx.begin();
            this.repository.create(joueur);
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

    public void renommeJoueur(Long id , String nom){

        Joueur joueur = getJoueurById(id);

        EntityManager em = null;
        EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction() ;
            tx.begin();
            joueur.setNom(nom);
            Joueur joueurPersi = (Joueur) em.merge(joueur);
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
    public List<Joueur> getAllJoueur(){
        List<Joueur> joueurList = new ArrayList<>();
        EntityManager em = null;
        EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction() ;
            tx.begin();
            joueurList = this.repository.getAll() ;
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
        return joueurList;

    }
    public  Joueur getJoueurById(Long id){


        Joueur joueur= new Joueur();
        EntityManager em = null;
        EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction() ;
            tx.begin();
            joueur = this.repository.getJoueurById(id);
            Hibernate.initialize(joueur.getEpreuves());
            em.flush();
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

        return  joueur;
    }
    public void deleteJoueur(Long id){
        EntityManager em = null;
        EntityTransaction tx = null ;
        try {
            em = EntityManagerHolder.getEntityManager();
            tx = em.getTransaction() ;
            tx.begin();
            this.repository.deleteJoueur(id);
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
