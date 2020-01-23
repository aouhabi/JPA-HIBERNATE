package com.mycompany.tennis.Repository;

import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.EntityManagerHolder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;

public class JoueurRepositoryImpl {

    private Connection conn =null;



    public void create (Joueur joueur){
        EntityManager em = EntityManagerHolder.getEntityManager() ;
            em.persist(joueur);

    }


    public List<Joueur> getAll(){
        EntityManager em = EntityManagerHolder.getEntityManager() ;
        TypedQuery<Joueur> query = em.createNamedQuery("select_all",Joueur.class);
        return  query.getResultList();
    }

    public Joueur getJoueurById(Long id) {
        EntityManager em = EntityManagerHolder.getEntityManager() ;
        Joueur joueur = em.find(Joueur.class,id);
        return  joueur;
    }

    public void deleteJoueur(Long id){
        EntityManager em = EntityManagerHolder.getEntityManager() ;
        Joueur joueur = getJoueurById(id);
        em.remove(joueur);
        System.out.println("utilisateur supprimer "+joueur);
    }
}
