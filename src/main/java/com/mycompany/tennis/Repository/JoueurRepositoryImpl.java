package com.mycompany.tennis.Repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.Entity.Joueur;
import com.mycompany.tennis.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    private Connection conn =null;



    public void create (Joueur joueur){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
            session.persist(joueur);

    }


    public List<Joueur> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createNamedQuery("select_all",Joueur.class);
        return  query.getResultList();
    }

    public Joueur getJoueurById(Long id) {
        Joueur joueur = new Joueur();
         Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
         joueur = session.get(Joueur.class,id);
        return  joueur;
    }

    public void deleteJoueur(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Joueur joueur = getJoueurById(id);
        session.delete(joueur);
        System.out.println("utilisateur supprimer "+joueur);
    }
}
